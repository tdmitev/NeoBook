package org.neobook.userservice.dto;

import java.util.List;
import java.util.UUID;

public record CreateParentDto(
        UUID keycloakUserId,
        String phone,
        List<UUID> studentKeycloakUserIds
) {}
