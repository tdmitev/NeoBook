package org.neobook.userservice.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.neobook.userservice.dto.CreateTeacherDto;
import org.neobook.userservice.dto.TeacherDto;
import org.neobook.userservice.dto.UpdateTeacherDto;
import org.neobook.userservice.model.Teacher;
import org.neobook.userservice.service.KeycloakService;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.FIELD,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class TeacherMapper {

    /* ---------- DI ---------- */
    @Autowired
    protected KeycloakService keycloakService;            // унаследява се в имплементацията

    /* ---------- Entity ➜ DTO ---------- */
    @Mapping(target = "firstName",
            expression = "java(keycloakService.getUser(entity.getKeycloakUserId()).getFirstName())")
    @Mapping(target = "lastName",
            expression = "java(keycloakService.getUser(entity.getKeycloakUserId()).getLastName())")
    @Mapping(target = "email",
            expression = "java(keycloakService.getUser(entity.getKeycloakUserId()).getEmail())")
    @Mapping(target = "roles",
            expression = "java(keycloakService.getUserRoles(entity.getKeycloakUserId()))")
    public abstract TeacherDto toDto(Teacher entity);

    /* ---------- Create DTO ➜ Entity ---------- */
    @Mapping(target = "id", ignore = true)
    public abstract Teacher toEntity(CreateTeacherDto dto);

    /* ---------- Update DTO ➜ Entity ---------- */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "keycloakUserId", ignore = true)
    public abstract void updateEntityFromDto(UpdateTeacherDto dto, @MappingTarget Teacher entity);
}