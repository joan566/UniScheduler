package org.unischeduler.academic_programming.domain.inputports.schedule;

import org.unischeduler.academic_programming.application.services.out.GetScheduleCommand;
import org.unischeduler.academic_programming.application.services.out.dtos.GetScheduleResponse;

public interface GetScheduleUseCase {
    GetScheduleResponse execute(GetScheduleCommand command);
}
