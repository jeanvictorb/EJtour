package com.example.demo.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()  // Desativa proteção CSRF (para APIs REST)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api").permitAll() // Permite criar usuários sem autenticação
                .anyRequest().permitAll()  // Permite todas as outras requisições
            )
            .formLogin().disable()  // Desativa o formulário de login
            .httpBasic().disable(); // Desativa autenticação básica
        
        return http.build();
    }
}
