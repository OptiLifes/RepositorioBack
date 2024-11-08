package com.optilife.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/api/usuarios/login", "/api/usuarios/registro").permitAll() // Usar requestMatchers
                        .anyRequest().authenticated() // Proteger todas las demás rutas
                )
                .logout((logout) -> logout
                        .logoutUrl("/api/usuarios/logout")
                        .logoutSuccessUrl("/api/usuarios/login")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                )
                .csrf().disable(); // Desactiva CSRF para simplificar, aunque es recomendable habilitarlo en producción

        return http.build();
    }
}
