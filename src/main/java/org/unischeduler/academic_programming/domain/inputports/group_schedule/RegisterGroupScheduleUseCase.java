package org.unischeduler.academic_programming.domain.inputports.group_schedule;

import org.unischeduler.academic_programming.application.services.in.group_schedules.RegisterGroupScheduleCommand;
import org.unischeduler.academic_programming.application.services.in.group_schedules.dtos.RegisterGroupScheduleResponse;

public interface RegisterGroupScheduleUseCase {
    RegisterGroupScheduleResponse execute(RegisterGroupScheduleCommand command);
}
