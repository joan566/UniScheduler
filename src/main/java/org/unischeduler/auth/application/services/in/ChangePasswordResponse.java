package org.unischeduler.auth.application.services.in;

public class ChangePasswordResponse {
    private final boolean successfully;
    private final String message;

    public ChangePasswordResponse(boolean successfully, String message) {
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
