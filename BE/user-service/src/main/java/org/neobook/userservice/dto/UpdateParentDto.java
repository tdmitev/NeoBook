package org.neobook.userservice.dto;

import java.util.List;
import java.util.UUID;

public record UpdateParentDto(
        String phone,
        List<UUID> studentKeycloakUserIds
) {}
