package com.proyecto.gestion_vehiculos.security;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.proyecto.gestion_vehiculos.USUARIO.entity.Usuario;
import com.proyecto.gestion_vehiculos.USUARIO.repository.UsuarioRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(
            JwtUtil jwtUtil,
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {

        this.jwtUtil = jwtUtil;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestParam String user,
            @RequestParam String pass) {

        Optional<Usuario> usuario =
                usuarioRepository.findById_Login(user);

        if (usuario.isPresent()) {

            Usuario u = usuario.get();

            if (passwordEncoder.matches(pass, u.getPassword())) {

                String token =
                        jwtUtil.generarToken(user);

                return ResponseEntity.ok(token);
            }
        }

        return ResponseEntity.status(401)
                .body("Credenciales inválidas");
    }
}