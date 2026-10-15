package org.unischeduler.enrollment.domain.inputports;

import org.unischeduler.enrollment.application.services.in.register.RegisterStudentCommand;
import org.unischeduler.enrollment.application.services.in.register.dtos.RegisterStudentResponse;

public interface RegisterStudentUseCase {
    RegisterStudentResponse execute(RegisterStudentCommand command);
}
