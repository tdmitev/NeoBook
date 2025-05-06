package org.neobook.userservice.dto;

import java.util.List;
import java.util.UUID;

public record ParentDto(
        Integer id,
        UUID keycloakUserId,
        String firstName,
        String lastName,
        String phone,
        List<UUID> studentKeycloakUserIds
) {}

