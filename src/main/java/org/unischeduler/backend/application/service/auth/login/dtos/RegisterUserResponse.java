package org.unischeduler.backend.application.service.auth.login.dtos;

public class RegisterUserResponse {
    private final boolean successfully;
    private final String message;
    private final RegisterUserInfo user;

    public RegisterUserResponse(boolean successfully, String message, RegisterUserInfo user) {
        this.successfully = successfully;
        this.message = message;
        this.user = user;
    }

    public boolean isSuccessfully() {
        return successfully;
    }

    public String getMessage() {
        return message;
    }

    public RegisterUserInfo getUser() {
        return user;
    }
}
