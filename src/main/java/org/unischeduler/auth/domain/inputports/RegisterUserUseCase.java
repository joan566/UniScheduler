package org.unischeduler.auth.domain.inputports;

import org.unischeduler.auth.application.services.in.login.RegisterUserCommand;
import org.unischeduler.auth.application.services.in.login.dtos.RegisterUserResponse;

public interface RegisterUserUseCase {
    RegisterUserResponse execute(RegisterUserCommand command);
}
