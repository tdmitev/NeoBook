package org.neobook.userservice.mapper;

import org.mapstruct.*;
import org.neobook.userservice.dto.*;
import org.neobook.userservice.model.Student;
import org.neobook.userservice.service.KeycloakService;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.FIELD,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class StudentMapper {

    @Autowired
    protected KeycloakService keycloakService;

    // 1) MapStruct мапва id, keycloakUserId, phone автоматично
    // 2) Имплементира се firstName/lastName от Keycloak
    @Mapping(target = "firstName",
            expression = "java(keycloakService.getUser(entity.getKeycloakUserId()).getFirstName())")
    @Mapping(target = "lastName",
            expression = "java(keycloakService.getUser(entity.getKeycloakUserId()).getLastName())")
    // 3) Мапват се parents → parentKeycloakUserIds
    @Mapping(target = "parentKeycloakUserIds",
            source = "parents",
            qualifiedByName = "parentsToIds")
    public abstract StudentDto toDto(Student entity);

    // При създаване се задават само примитивните полета
    // parents се попълват в сервиза
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "parents", ignore = true)
    public abstract Student toEntity(CreateStudentDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "parents", ignore = true)
    public abstract void updateEntityFromDto(UpdateStudentDto dto, @MappingTarget Student entity);

    @Named("parentsToIds")
    static java.util.List<java.util.UUID> parentsToIds(java.util.Set<org.neobook.userservice.model.Parent> parents) {
        if (parents == null) return java.util.Collections.emptyList();
        return parents.stream()
                .map(org.neobook.userservice.model.Parent::getKeycloakUserId)
                .toList();
    }
}