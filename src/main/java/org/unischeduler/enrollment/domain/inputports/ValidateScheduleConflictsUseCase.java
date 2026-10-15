package org.unischeduler.enrollment.domain.inputports;

import org.unischeduler.enrollment.application.services.in.validate.ValidateScheduleConflictsCommand;
import org.unischeduler.enrollment.application.services.in.validate.dtos.ValidateScheduleConflictsResponse;

public interface ValidateScheduleConflictsUseCase {
    ValidateScheduleConflictsResponse execute(ValidateScheduleConflictsCommand command);
}
