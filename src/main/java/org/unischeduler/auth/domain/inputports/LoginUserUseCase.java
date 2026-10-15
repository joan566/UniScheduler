package org.unischeduler.auth.domain.inputports;

import org.unischeduler.auth.application.services.in.login.LoginUserCommand;
import org.unischeduler.auth.application.services.in.login.dtos.LoginUserResponse;

public interface LoginUserUseCase {
    LoginUserResponse execute(LoginUserCommand command);

}
