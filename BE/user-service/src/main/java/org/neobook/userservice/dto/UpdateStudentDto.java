package org.neobook.userservice.dto;

import java.util.List;
import java.util.UUID;

public record UpdateStudentDto(
        String phone,
        List<UUID> parentKeycloakUserIds,
        Long schoolClassId
) {}
