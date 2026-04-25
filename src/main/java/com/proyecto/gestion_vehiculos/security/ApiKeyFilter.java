package com.proyecto.gestion_vehiculos.security;

import java.io.IOException;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.proyecto.gestion_vehiculos.USUARIO.entity.Usuario;
import com.proyecto.gestion_vehiculos.USUARIO.repository.UsuarioRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ApiKeyFilter extends OncePerRequestFilter {

    private final UsuarioRepository usuarioRepository;

    public ApiKeyFilter(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        // Rutas públicas
        if (path.startsWith("/auth/")
            || (path.equals("/personas") && request.getMethod().equals("POST"))
            || path.equals("/api/vehiculos/vencidos")
            || path.startsWith("/api/vehiculos/placa/")
            || path.startsWith("/api/vehiculos/por-vencer")
            || path.equals("/api/vehiculos/conductores-operativos")
            || path.equals("/api/personas/totales")
            || path.startsWith("/swagger-ui/")
            || path.startsWith("/v3/api-docs")) {
        
            filterChain.doFilter(request, response);
            return;
        }

        String apiKey = request.getHeader("x-api-key");

        if (apiKey == null || apiKey.isBlank()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("API KEY requerida");
            return;
        }

        Optional<Usuario> usuario =
                usuarioRepository.findByApiKey(apiKey);

        if (usuario.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("API KEY invalida");
            return;
        }

        filterChain.doFilter(request, response);
    }
}