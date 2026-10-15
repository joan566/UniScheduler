package org.unischeduler.backend.application.service.auth.login.dtos;

public class RegisterUserInfo {
    private final String userId;
    private final String fullName;
    private final String email;
    private final String role;

    public RegisterUserInfo(String userId, String fullName, String email, String role) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }
}
