package org.neobook.notebookservice.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    @Qualifier("userServiceClient")
    public WebClient userServiceClient(WebClient.Builder builder) {
        return builder.baseUrl("http://user-service:8081/api").build();
    }

    @Bean
    @Qualifier("schoolServiceClient")
    public WebClient schoolServiceClient(WebClient.Builder builder) {
        return builder.baseUrl("http://school-service:8082/api").build();
    }
}
