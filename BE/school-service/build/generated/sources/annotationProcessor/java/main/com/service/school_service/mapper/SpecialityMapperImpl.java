package com.service.school_service.mapper;

import com.service.school_service.dto.CreateSpecialityDto;
import com.service.school_service.dto.SpecialityDto;
import com.service.school_service.model.Speciality;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-17T20:08:42+0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.13.jar, environment: Java 17.0.12 (JetBrains s.r.o.)"
)
@Component
public class SpecialityMapperImpl implements SpecialityMapper {

    @Override
    public Speciality toEntity(CreateSpecialityDto dto) {
        if ( dto == null ) {
            return null;
        }

        Speciality speciality = new Speciality();

        speciality.setName( dto.name() );

        return speciality;
    }

    @Override
    public Speciality toEntity(SpecialityDto dto) {
        if ( dto == null ) {
            return null;
        }

        Speciality speciality = new Speciality();

        speciality.setId( dto.id() );
        speciality.setName( dto.name() );

        return speciality;
    }

    @Override
    public SpecialityDto toDto(Speciality entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        String name = null;

        id = entity.getId();
        name = entity.getName();

        SpecialityDto specialityDto = new SpecialityDto( id, name );

        return specialityDto;
    }
}
