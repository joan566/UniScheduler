package org.unischeduler.auth.domain.inputports;

import org.unischeduler.auth.application.services.in.ChangePasswordCommand;
import org.unischeduler.auth.application.services.in.ChangePasswordResponse;

public interface ChangePasswordUseCase {
    ChangePasswordResponse execute(ChangePasswordCommand command);
}
