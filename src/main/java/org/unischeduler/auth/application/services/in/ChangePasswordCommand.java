package org.unischeduler.auth.application.services.in;

public record ChangePasswordCommand(String currentPassword, String newPassword, String confirmPassword) {
}
