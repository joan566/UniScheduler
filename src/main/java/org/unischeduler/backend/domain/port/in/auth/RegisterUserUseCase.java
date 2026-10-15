package org.unischeduler.backend.domain.port.in.auth;

import org.unischeduler.backend.application.service.auth.login.RegisterUserCommand;
import org.unischeduler.backend.application.service.auth.login.dtos.RegisterUserResponse;

public interface RegisterUserUseCase {
    RegisterUserResponse execute(RegisterUserCommand command);
}
