package org.neobook.userservice.dto;

import java.util.List;

public record UpdateTeacherDto(
        String phone,
        Long schoolId,
        List<Long> subjectIds
) {}
