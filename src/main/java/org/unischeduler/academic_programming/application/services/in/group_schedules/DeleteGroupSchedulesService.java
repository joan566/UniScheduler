package org.unischeduler.academic_programming.application.services.in.group_schedules;

import org.unischeduler.academic_programming.domain.inputports.group_schedule.DeleteGroupSchedulesUseCase;
import org.unischeduler.academic_programming.domain.outputports.GroupScheduleRepository;

public class DeleteGroupSchedulesService
        implements DeleteGroupSchedulesUseCase {

    private final GroupScheduleRepository groupScheduleRepository;

    public DeleteGroupSchedulesService(
            GroupScheduleRepository groupScheduleRepository
    ) {
        this.groupScheduleRepository = groupScheduleRepository;
    }

    @Override
    public void execute(DeleteGroupSchedulesCommand command) {
        groupScheduleRepository.deleteAllWhereGroupId(
                command.getGroupId()
        );
    }
}