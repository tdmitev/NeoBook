package com.service.school_service.mapper;

import com.service.school_service.dto.CreateSchoolDto;
import com.service.school_service.dto.SchoolClassDto;
import com.service.school_service.dto.SchoolDto;
import com.service.school_service.dto.StudentDto;
import com.service.school_service.dto.TeacherDto;
import com.service.school_service.enums.GradeLetter;
import com.service.school_service.model.School;
import com.service.school_service.model.SchoolClass;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-17T19:29:13+0300",
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
        List<SchoolClassDto> classes = null;

        id = school.getId();
        name = school.getName();
        address = school.getAddress();
        classes = schoolClassListToSchoolClassDtoList( school.getClasses() );

        SchoolDto schoolDto = new SchoolDto( id, name, address, classes );

        return schoolDto;
    }

    protected SchoolClassDto schoolClassToSchoolClassDto(SchoolClass schoolClass) {
        if ( schoolClass == null ) {
            return null;
        }

        Long id = null;
        int gradeLevel = 0;
        GradeLetter letter = null;
        UUID teacherId = null;
        TeacherDto teacherDto = null;
        HashSet<StudentDto> students = null;

        id = schoolClass.getId();
        gradeLevel = schoolClass.getGradeLevel();
        letter = schoolClass.getLetter();
        teacherId = schoolClass.getTeacherId();
        teacherDto = schoolClass.getTeacherDto();
        Set<StudentDto> set = schoolClass.getStudents();
        if ( set != null ) {
            students = new HashSet<StudentDto>( set );
        }

        Long schoolId = null;
        Long specialityId = null;
        Long scheduleId = null;

        SchoolClassDto schoolClassDto = new SchoolClassDto( id, gradeLevel, letter, schoolId, specialityId, teacherId, teacherDto, students, scheduleId );

        return schoolClassDto;
    }

    protected List<SchoolClassDto> schoolClassListToSchoolClassDtoList(List<SchoolClass> list) {
        if ( list == null ) {
            return null;
        }

        List<SchoolClassDto> list1 = new ArrayList<SchoolClassDto>( list.size() );
        for ( SchoolClass schoolClass : list ) {
            list1.add( schoolClassToSchoolClassDto( schoolClass ) );
        }

        return list1;
    }
}
