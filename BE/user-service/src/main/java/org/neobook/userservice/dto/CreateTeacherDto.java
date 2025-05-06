package org.neobook.userservice.dto;

import java.util.UUID;

public record CreateTeacherDto(
        UUID keycloakUserId,
        String phone
) {}
