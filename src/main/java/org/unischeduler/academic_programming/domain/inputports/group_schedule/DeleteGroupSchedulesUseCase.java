package org.unischeduler.academic_programming.domain.inputports.group_schedule;

import org.unischeduler.academic_programming.application.services.in.group_schedules.DeleteGroupSchedulesCommand;

public interface DeleteGroupSchedulesUseCase {
    void execute(DeleteGroupSchedulesCommand command);
}
