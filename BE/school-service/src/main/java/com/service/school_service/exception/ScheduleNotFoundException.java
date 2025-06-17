package com.service.school_service.exception;

public class ScheduleNotFoundException extends RuntimeException {
    public ScheduleNotFoundException(Long id) {
        super("Schedule with ID " + id + " not found.");
    }
}
