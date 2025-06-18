package org.neobook.userservice.dto;

import java.util.List;
import java.util.UUID;

public record CreateStudentDto(
        UUID keycloakUserId,
        String phone,
        List<UUID> parentKeycloakUserIds,
        Long schoolClassId
) {}
