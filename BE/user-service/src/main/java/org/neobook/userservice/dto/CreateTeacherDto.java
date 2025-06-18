package org.neobook.userservice.dto;

import java.util.List;
import java.util.UUID;

public record CreateTeacherDto(
        UUID keycloakUserId,
        String phone,
        Long schoolId,
        List<Long> subjectIds

) {}
