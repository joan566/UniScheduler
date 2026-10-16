package org.unischeduler.auth.presentation.dtos;

public record ChangePasswordRequest(String currentPassword, String newPassword, String confirmPassword) {
}
