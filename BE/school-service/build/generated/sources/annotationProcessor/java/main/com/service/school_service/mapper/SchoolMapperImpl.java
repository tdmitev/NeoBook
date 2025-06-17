package com.service.school_service.mapper;

import com.service.school_service.dto.CreateSchoolDto;
import com.service.school_service.dto.SchoolDto;
import com.service.school_service.model.School;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-17T20:08:42+0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.13.jar, environment: Java 17.0.12 (JetBrains s.r.o.)"
)
@Component
public class SchoolMapperImpl implements SchoolMapper {

    @Override
    public School toEntity(SchoolDto schoolDto) {
        if ( schoolDto == null ) {
            return null;
        }

        School school = new School();

        school.setName( schoolDto.name() );
        school.setAddress( schoolDto.address() );

        return school;
    }

    @Override
    public School toEntity(CreateSchoolDto createSchoolDto) {
        if ( createSchoolDto == null ) {
            return null;
        }

        School school = new School();

        school.setName( createSchoolDto.name() );
        school.setAddress( createSchoolDto.address() );

        return school;
    }

    @Override
    public SchoolDto toDto(School school) {
        if ( school == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String address = null;

        id = school.getId();
        name = school.getName();
        address = school.getAddress();

        SchoolDto schoolDto = new SchoolDto( id, name, address );

        return schoolDto;
    }
}
