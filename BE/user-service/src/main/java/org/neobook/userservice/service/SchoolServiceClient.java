package org.neobook.userservice.service;

import org.neobook.userservice.dto.SchoolClassClientDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class SchoolServiceClient {

    private final WebClient webClient;

    public SchoolServiceClient(@Qualifier("schoolWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    /**
     * Валидация и/или обогатяване на schoolClassId.
     * Чете от school-service
     */
    public SchoolClassClientDto getSchoolClassById(Long classId) {
        return webClient.get()
                .uri("/school-classes/{id}", classId)
                .retrieve()
                .bodyToMono(SchoolClassClientDto.class)
                .block();
    }
}
