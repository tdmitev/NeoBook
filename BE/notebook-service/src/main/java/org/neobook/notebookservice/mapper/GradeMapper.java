package org.neobook.notebookservice.mapper;

import org.mapstruct.*;
import org.neobook.notebookservice.dto.CreateGradeDto;
import org.neobook.notebookservice.dto.GradeDto;
import org.neobook.notebookservice.dto.UpdateGradeDto;
import org.neobook.notebookservice.model.Grade;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GradeMapper {

    GradeDto toDto(Grade entity);

    @Mapping(target = "id", ignore = true)
    Grade toEntity(CreateGradeDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(UpdateGradeDto dto, @MappingTarget Grade entity);
}
