package org.unischeduler.academic_programming.domain.inputports.group_schedule;

import org.unischeduler.academic_programming.application.services.in.group_schedules.UpdateGroupScheduleCommand;
import org.unischeduler.academic_programming.application.services.in.group_schedules.dtos.UpdateGroupScheduleResponse;

public interface UpdateGroupScheduleUseCase {
    UpdateGroupScheduleResponse execute(UpdateGroupScheduleCommand command);
}
