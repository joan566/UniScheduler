package org.unischeduler.enrollment.domain.inputports;

import org.unischeduler.enrollment.application.services.in.validate.ValidateCreditLimitCommand;
import org.unischeduler.enrollment.application.services.in.validate.dtos.ValidateCreditLimitResponse;

public interface ValidateCreditLimitUseCase {
    ValidateCreditLimitResponse execute(ValidateCreditLimitCommand command);
}
