package org.unischeduler.academic_catalog.domain.exceptions;

public class PeriodActiveNotFoundException extends RuntimeException {
    public PeriodActiveNotFoundException(String message) {
        super(message);
    }
}
