package com.example.demo.Security;

import javax.swing.text.PasswordView;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;


@Configuration
public class SecurityConfig {

    @Bean
    public PasswordView passwordEncoder() {
        return new PasswordView(null);
    }

    @Bean
    public Authentication authenticationManager(AopAutoConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(@SuppressWarnings("rawtypes") HttpEntity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}
