package com.service.school_service.mapper;

import com.service.school_service.dto.CreateSubjectAssignmentDto;
import com.service.school_service.dto.SubjectAssignmentDto;
import com.service.school_service.enums.TimeSlot;
import com.service.school_service.model.SubjectAssignment;
import java.time.DayOfWeek;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-17T19:29:13+0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.13.jar, environment: Java 17.0.12 (JetBrains s.r.o.)"
)
@Component
public class SubjectAssignmentMapperImpl implements SubjectAssignmentMapper {

    @Override
    public SubjectAssignment toEntity(CreateSubjectAssignmentDto subjectAssignmentDto) {
        if ( subjectAssignmentDto == null ) {
            return null;
        }

        SubjectAssignment subjectAssignment = new SubjectAssignment();

        subjectAssignment.setDayOfWeek( subjectAssignmentDto.dayOfWeek() );
        subjectAssignment.setTimeSlot( subjectAssignmentDto.timeSlot() );
        subjectAssignment.setTeacherId( subjectAssignmentDto.teacherId() );

        return subjectAssignment;
    }

    @Override
    public SubjectAssignment toEntity(SubjectAssignmentDto subjectAssignmentDto) {
        if ( subjectAssignmentDto == null ) {
            return null;
        }

        SubjectAssignment subjectAssignment = new SubjectAssignment();

        subjectAssignment.setDayOfWeek( subjectAssignmentDto.dayOfWeek() );
        subjectAssignment.setTimeSlot( subjectAssignmentDto.timeSlot() );
        subjectAssignment.setTeacherId( subjectAssignmentDto.teacherId() );

        return subjectAssignment;
    }

    @Override
    public SubjectAssignmentDto toDto(SubjectAssignment subjectAssignment) {
        if ( subjectAssignment == null ) {
            return null;
        }

        Long id = null;
        DayOfWeek dayOfWeek = null;
        TimeSlot timeSlot = null;
        UUID teacherId = null;

        id = subjectAssignment.getId();
        dayOfWeek = subjectAssignment.getDayOfWeek();
        timeSlot = subjectAssignment.getTimeSlot();
        teacherId = subjectAssignment.getTeacherId();

        Long subjectId = null;
        Long programId = null;

        SubjectAssignmentDto subjectAssignmentDto = new SubjectAssignmentDto( id, dayOfWeek, timeSlot, teacherId, subjectId, programId );

        return subjectAssignmentDto;
    }
}
