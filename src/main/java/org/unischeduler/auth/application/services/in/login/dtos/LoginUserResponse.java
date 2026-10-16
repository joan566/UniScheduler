package org.unischeduler.auth.application.services.in.login.dtos;

public record LoginUserResponse(boolean successfully, String message, UserInfo user, String accessToken) {
}
