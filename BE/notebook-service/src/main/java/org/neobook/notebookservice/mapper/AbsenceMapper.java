package org.neobook.notebookservice.mapper;

import org.mapstruct.*;
import org.neobook.notebookservice.dto.AbsenceDto;
import org.neobook.notebookservice.dto.CreateAbsenceDto;
import org.neobook.notebookservice.dto.UpdateAbsenceDto;
import org.neobook.notebookservice.model.Absence;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AbsenceMapper {

    AbsenceDto toDto(Absence entity);

    @Mapping(target = "id", ignore = true)
    Absence toEntity(CreateAbsenceDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(UpdateAbsenceDto dto, @MappingTarget Absence entity);
}
