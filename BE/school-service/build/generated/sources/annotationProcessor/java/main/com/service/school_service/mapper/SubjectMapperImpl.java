package com.service.school_service.mapper;

import com.service.school_service.dto.CreateSubjectDto;
import com.service.school_service.dto.SubjectDto;
import com.service.school_service.model.Subject;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-08T09:39:43+0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.13.jar, environment: Java 17.0.14 (Amazon.com Inc.)"
)
@Component
public class SubjectMapperImpl implements SubjectMapper {

    @Override
    public Subject toEntity(SubjectDto subjectDto) {
        if ( subjectDto == null ) {
            return null;
        }

        Subject subject = new Subject();

        subject.setId( subjectDto.id() );
        subject.setName( subjectDto.name() );
        Set<UUID> set = subjectDto.teacherIds();
        if ( set != null ) {
            subject.setTeacherIds( new LinkedHashSet<UUID>( set ) );
        }

        return subject;
    }

    @Override
    public Subject toEntity(CreateSubjectDto subjectDto) {
        if ( subjectDto == null ) {
            return null;
        }

        Subject subject = new Subject();

        subject.setName( subjectDto.name() );
        Set<UUID> set = subjectDto.teacherIds();
        if ( set != null ) {
            subject.setTeacherIds( new LinkedHashSet<UUID>( set ) );
        }

        return subject;
    }

    @Override
    public SubjectDto toDto(Subject subject) {
        if ( subject == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        Set<UUID> teacherIds = null;

        id = subject.getId();
        name = subject.getName();
        Set<UUID> set = subject.getTeacherIds();
        if ( set != null ) {
            teacherIds = new LinkedHashSet<UUID>( set );
        }

        SubjectDto subjectDto = new SubjectDto( id, name, teacherIds );

        return subjectDto;
    }
}
