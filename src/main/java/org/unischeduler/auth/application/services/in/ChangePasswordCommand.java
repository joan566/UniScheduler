package org.unischeduler.auth.application.services.in;

public class ChangePasswordCommand {
    private final Long userId;
    private final String currentPassword;
    private final String newPassword;
    private final String confirmPassword;

    public ChangePasswordCommand(Long userId, String currentPassword, String newPassword, String confirmPassword) {
        this.userId = userId;
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }

    public Long getUserId() {
        return userId;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
}
