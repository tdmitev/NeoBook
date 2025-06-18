package org.neobook.notebookservice.dto;

import java.time.LocalDate;
import java.util.UUID;

public record GradeDto(
        String id,
        UUID studentId,
        UUID teacherId,
        Long subjectId,
        int value,
        LocalDate date
) {}
