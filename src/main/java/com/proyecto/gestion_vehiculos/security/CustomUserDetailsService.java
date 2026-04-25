package com.proyecto.gestion_vehiculos.security;


import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.proyecto.gestion_vehiculos.USUARIO.entity.Usuario;
import com.proyecto.gestion_vehiculos.USUARIO.repository.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(
            UsuarioRepository usuarioRepository) {

        this.usuarioRepository = usuarioRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username)
        throws UsernameNotFoundException {
                Usuario usuario = usuarioRepository
                .findById_Login(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuario no encontrado"));

                return User.builder()
                .username(usuario.getId().getLogin())
                .password(usuario.getPassword())
                .authorities(
                        new SimpleGrantedAuthority(usuario.getRol())
                )
            .build();
        }
}