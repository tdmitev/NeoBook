package org.neobook.userservice.dto;

public record SchoolClassClientDto(
        Long id,
        String name       // или каквито полета ти трябват от school-service
) {}
