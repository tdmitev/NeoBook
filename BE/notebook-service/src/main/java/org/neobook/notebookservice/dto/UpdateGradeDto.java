package org.neobook.notebookservice.dto;

import java.time.LocalDate;

public record UpdateGradeDto(
        Integer value,
        LocalDate date
) {}
