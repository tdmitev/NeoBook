package com.service.school_service.mapper;

import com.service.school_service.dto.CreateScheduleDto;
import com.service.school_service.dto.ScheduleDto;
import com.service.school_service.dto.SubjectAssignmentDto;
import com.service.school_service.enums.TimeSlot;
import com.service.school_service.model.Schedule;
import com.service.school_service.model.SubjectAssignment;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-08T09:39:43+0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.13.jar, environment: Java 17.0.14 (Amazon.com Inc.)"
)
@Component
public class ScheduleMapperImpl implements ScheduleMapper {

    @Override
    public Schedule toEntity(ScheduleDto scheduleDto) {
        if ( scheduleDto == null ) {
            return null;
        }

        Schedule schedule = new Schedule();

        return schedule;
    }

    @Override
    public Schedule toEntity(CreateScheduleDto dto) {
        if ( dto == null ) {
            return null;
        }

        Schedule schedule = new Schedule();

        return schedule;
    }

    @Override
    public ScheduleDto toDto(Schedule schedule) {
        if ( schedule == null ) {
            return null;
        }

        Long id = null;
        List<SubjectAssignmentDto> subjectAssignments = null;

        id = schedule.getId();
        subjectAssignments = subjectAssignmentListToSubjectAssignmentDtoList( schedule.getSubjectAssignments() );

        Long schoolClassId = null;

        ScheduleDto scheduleDto = new ScheduleDto( id, schoolClassId, subjectAssignments );

        return scheduleDto;
    }

    protected SubjectAssignmentDto subjectAssignmentToSubjectAssignmentDto(SubjectAssignment subjectAssignment) {
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
        Long scheduleId = null;

        SubjectAssignmentDto subjectAssignmentDto = new SubjectAssignmentDto( id, dayOfWeek, timeSlot, teacherId, subjectId, scheduleId );

        return subjectAssignmentDto;
    }

    protected List<SubjectAssignmentDto> subjectAssignmentListToSubjectAssignmentDtoList(List<SubjectAssignment> list) {
        if ( list == null ) {
            return null;
        }

        List<SubjectAssignmentDto> list1 = new ArrayList<SubjectAssignmentDto>( list.size() );
        for ( SubjectAssignment subjectAssignment : list ) {
            list1.add( subjectAssignmentToSubjectAssignmentDto( subjectAssignment ) );
        }

        return list1;
    }
}
