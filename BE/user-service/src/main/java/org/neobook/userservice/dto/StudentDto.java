package org.neobook.userservice.dto;

import java.util.List;
import java.util.UUID;

public record StudentDto(
        Integer id,
        UUID keycloakUserId,
        String firstName,
        String lastName,
        String phone,
        List<UUID> parentKeycloakUserIds,
        Long schoolClassId
) {}
