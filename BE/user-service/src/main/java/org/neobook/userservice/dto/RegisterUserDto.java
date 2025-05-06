package org.neobook.userservice.dto;

import java.util.UUID;

public record RegisterUserDto(
        UUID keycloakUserId,
        String phone,
        String role
) {}