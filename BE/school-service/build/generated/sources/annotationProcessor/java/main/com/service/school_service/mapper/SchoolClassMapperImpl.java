package com.service.school_service.mapper;

import com.service.school_service.dto.CreateSchoolClassDto;
import com.service.school_service.dto.SchoolClassDto;
import com.service.school_service.dto.StudentDto;
import com.service.school_service.dto.TeacherDto;
import com.service.school_service.enums.GradeLetter;
import com.service.school_service.model.School;
import com.service.school_service.model.SchoolClass;
import com.service.school_service.model.Speciality;
import java.util.HashSet;
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
public class SchoolClassMapperImpl implements SchoolClassMapper {

    @Override
    public SchoolClass toEntity(SchoolClassDto schoolClassDto) {
        if ( schoolClassDto == null ) {
            return null;
        }

        SchoolClass schoolClass = new SchoolClass();

        schoolClass.setSchool( idToSchool( schoolClassDto.schoolId() ) );
        schoolClass.setSpeciality( idToSpeciality( schoolClassDto.specialityId() ) );
        schoolClass.setId( schoolClassDto.id() );
        schoolClass.setGradeLevel( schoolClassDto.gradeLevel() );
        schoolClass.setLetter( schoolClassDto.letter() );
        schoolClass.setTeacherId( schoolClassDto.teacherId() );

        return schoolClass;
    }

    @Override
    public SchoolClass toEntity(CreateSchoolClassDto createSchoolClassDto) {
        if ( createSchoolClassDto == null ) {
            return null;
        }

        SchoolClass schoolClass = new SchoolClass();

        schoolClass.setSchool( idToSchool( createSchoolClassDto.schoolId() ) );
        schoolClass.setSpeciality( idToSpeciality( createSchoolClassDto.specialityId() ) );
        schoolClass.setGradeLevel( createSchoolClassDto.gradeLevel() );
        schoolClass.setLetter( createSchoolClassDto.letter() );
        schoolClass.setTeacherId( createSchoolClassDto.teacherId() );

        return schoolClass;
    }

    @Override
    public SchoolClassDto toDto(SchoolClass schoolClass) {
        if ( schoolClass == null ) {
            return null;
        }

        Long schoolId = null;
        Long specialityId = null;
        Long id = null;
        int gradeLevel = 0;
        GradeLetter letter = null;
        UUID teacherId = null;
        TeacherDto teacherDto = null;
        HashSet<StudentDto> students = null;

        schoolId = schoolClassSchoolId( schoolClass );
        specialityId = schoolClassSpecialityId( schoolClass );
        id = schoolClass.getId();
        gradeLevel = schoolClass.getGradeLevel();
        letter = schoolClass.getLetter();
        teacherId = schoolClass.getTeacherId();
        teacherDto = schoolClass.getTeacherDto();
        Set<StudentDto> set = schoolClass.getStudents();
        if ( set != null ) {
            students = new HashSet<StudentDto>( set );
        }

        Long scheduleId = null;

        SchoolClassDto schoolClassDto = new SchoolClassDto( id, gradeLevel, letter, schoolId, specialityId, teacherId, teacherDto, students, scheduleId );

        return schoolClassDto;
    }

    private Long schoolClassSchoolId(SchoolClass schoolClass) {
        if ( schoolClass == null ) {
            return null;
        }
        School school = schoolClass.getSchool();
        if ( school == null ) {
            return null;
        }
        Long id = school.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long schoolClassSpecialityId(SchoolClass schoolClass) {
        if ( schoolClass == null ) {
            return null;
        }
        Speciality speciality = schoolClass.getSpeciality();
        if ( speciality == null ) {
            return null;
        }
        Long id = speciality.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
