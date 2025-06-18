package org.neobook.userservice.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    // 1) Общ билдър, който да се споделя навсякъде
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    // 2) Именуван WebClient, който ще сочи към school-service
    @Bean
    @Qualifier("schoolWebClient")
    public WebClient schoolWebClient(WebClient.Builder builder) {
        return builder
                .baseUrl("http://school-service:8082/api")
                .build();
    }
}