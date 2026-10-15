package org.unischeduler.academic_catalog.application.services.in.prerequisite.dtos;

public class RegisterPrerequisiteResponse {
    private final boolean successfully;
    private final String message;

    public RegisterPrerequisiteResponse(boolean successfully, String message) {
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
