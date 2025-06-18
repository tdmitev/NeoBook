package org.neobook.notebookservice.dto;

import java.time.LocalDate;
import java.util.UUID;

public record AbsenceDto(
        String id,
        UUID studentId,
        UUID teacherId,
        Long subjectId,
        LocalDate date,
        boolean excused
) {}
