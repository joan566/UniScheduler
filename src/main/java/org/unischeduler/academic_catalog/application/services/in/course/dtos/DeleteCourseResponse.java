package org.unischeduler.academic_catalog.application.services.in.course.dtos;

public class DeleteCourseResponse {
    private final boolean successfully;
    private final String message;

    public DeleteCourseResponse(boolean successfully, String message) {
        this.successfully = successfully;
        this.message = message;
    }

    public boolean isSuccessfully() {
        return successfully;
    }

    public String getMessage() {
        return message;
    }
}
