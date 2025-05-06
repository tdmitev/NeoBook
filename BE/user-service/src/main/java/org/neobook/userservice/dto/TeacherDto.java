package org.neobook.userservice.dto;

import java.util.List;
import java.util.UUID;

public record TeacherDto(
        Integer id,
        UUID keycloakUserId,
        String firstName,
        String lastName,
        String email,
        String phone,
        List<String> roles
) {}
