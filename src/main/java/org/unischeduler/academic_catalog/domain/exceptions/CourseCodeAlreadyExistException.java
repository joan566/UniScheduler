package org.unischeduler.academic_catalog.domain.exceptions;

public class CourseCodeAlreadyExistException extends RuntimeException {
    public CourseCodeAlreadyExistException(String message) {
        super(message);
    }
}
