package org.unischeduler.enrollment.domain.inputports;

import org.unischeduler.enrollment.application.services.in.validate.ValidatePrerequisiteCommand;
import org.unischeduler.enrollment.application.services.in.validate.dtos.ValidatePrerequisiteResponse;

public interface ValidatePrerequisiteUseCase {
    ValidatePrerequisiteResponse execute(ValidatePrerequisiteCommand command);
}
