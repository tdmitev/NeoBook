package org.example.apigateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                // 1) OIDC login (Code Flow) – пренасочва към Keycloak, ако няма токен
                .oauth2Login(withDefaults())
                // 2) след login – JWT validation за всички API routes
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(withDefaults()))
                .authorizeExchange(exchanges -> exchanges
                        // actuator публични
                        .pathMatchers("/actuator/**").permitAll()
                        // всичко останало – аутентикирано
                        .anyExchange().authenticated()
                );

        return http.build();
    }
}