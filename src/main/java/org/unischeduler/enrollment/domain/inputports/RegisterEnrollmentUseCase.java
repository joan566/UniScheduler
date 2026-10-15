package org.unischeduler.enrollment.domain.inputports;

import org.unischeduler.enrollment.application.services.in.register.RegisterEnrollmentCommand;
import org.unischeduler.enrollment.application.services.in.register.dtos.RegisterEnrollmentResponse;
import org.unischeduler.enrollment.application.services.in.register.dtos.RegisterStudentResponse;

public interface RegisterEnrollmentUseCase {
    RegisterEnrollmentResponse execute(RegisterEnrollmentCommand command);
}
