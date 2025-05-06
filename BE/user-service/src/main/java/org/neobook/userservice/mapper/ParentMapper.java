package org.neobook.userservice.mapper;

import org.mapstruct.*;
import org.neobook.userservice.dto.CreateParentDto;
import org.neobook.userservice.dto.ParentDto;
import org.neobook.userservice.dto.UpdateParentDto;
import org.neobook.userservice.model.Parent;
import org.neobook.userservice.service.KeycloakService;
import org.springframework.beans.factory.annotation.Autowired;


@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.FIELD,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ParentMapper {

    @Autowired
    protected KeycloakService keycloakService;

    @Mapping(target = "firstName",
            expression = "java(keycloakService.getUser(entity.getKeycloakUserId()).getFirstName())")
    @Mapping(target = "lastName",
            expression = "java(keycloakService.getUser(entity.getKeycloakUserId()).getLastName())")
    @Mapping(target = "studentKeycloakUserIds",
            source = "students",
            qualifiedByName = "studentsToIds")
    public abstract ParentDto toDto(Parent entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "students", ignore = true)
    public abstract Parent toEntity(CreateParentDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "students", ignore = true)
    public abstract void updateEntityFromDto(UpdateParentDto dto, @MappingTarget Parent entity);

    @Named("studentsToIds")
    static java.util.List<java.util.UUID> studentsToIds(java.util.Set<org.neobook.userservice.model.Student> students) {
        if (students == null) return java.util.Collections.emptyList();
        return students.stream()
                .map(org.neobook.userservice.model.Student::getKeycloakUserId)
                .toList();
    }
}
