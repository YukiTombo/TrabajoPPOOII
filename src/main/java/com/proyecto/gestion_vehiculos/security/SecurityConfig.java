package com.proyecto.gestion_vehiculos.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final ApiKeyFilter apiKeyFilter;

    @Autowired
    private JwtFilter jwtFilter;

    public SecurityConfig(ApiKeyFilter apiKeyFilter) {
        this.apiKeyFilter = apiKeyFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .sessionManagement(sess ->
                sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            .authorizeHttpRequests(auth -> auth

                // 🔓 LOGIN
                .requestMatchers("/auth/**").permitAll()
            
                // 🔓 CREAR PERSONA (usuario)
                .requestMatchers(HttpMethod.POST, "/personas").permitAll()
            
                // 🔓 SERVICIOS PÚBLICOS (LO QUE TE PIDEN)
                .requestMatchers(HttpMethod.GET, "/api/vehiculos/vencidos").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/vehiculos/placa/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/vehiculos/por-vencer/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/vehiculos/conductores-operativos").permitAll()
                .requestMatchers(HttpMethod.GET, "/personas/totales").permitAll()
            
                // 🔓 SWAGGER
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
            
                // 🔒 TODO LO DEMÁS
                .anyRequest().authenticated()
            ) 
            

            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(apiKeyFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {

        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}