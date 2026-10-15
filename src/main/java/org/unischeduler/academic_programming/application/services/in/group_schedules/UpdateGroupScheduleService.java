package org.unischeduler.academic_programming.application.services.in.group_schedules;

import org.unischeduler.academic_programming.application.services.in.group_schedules.dtos.UpdateGroupScheduleResponse;
import org.unischeduler.academic_programming.application.services.out.dtos.GroupScheduleInfo;
import org.unischeduler.academic_programming.domain.entity.GroupSchedule;
import org.unischeduler.academic_programming.domain.inputports.group_schedule.UpdateGroupScheduleUseCase;
import org.unischeduler.academic_programming.domain.outputports.GroupScheduleRepository;

import java.util.ArrayList;
import java.util.List;

public class UpdateGroupScheduleService
        implements UpdateGroupScheduleUseCase {

    private final GroupScheduleRepository groupScheduleRepository;

    public UpdateGroupScheduleService(
            GroupScheduleRepository groupScheduleRepository
    ) {
        this.groupScheduleRepository = groupScheduleRepository;
    }

    @Override
    public UpdateGroupScheduleResponse execute(
            UpdateGroupScheduleCommand command
    ) {

        groupScheduleRepository.deleteAllWhereGroupId(
                command.getGroupId()
        );

        List<GroupScheduleInfo> scheduleInfos = new ArrayList<>();

        for (GroupSchedule schedule : command.getSchedules()) {

            GroupSchedule saved =
                    groupScheduleRepository.save(schedule);

            scheduleInfos.add(
                    new GroupScheduleInfo(
                            saved.getGroupId(),
                            null, // CourseName no está disponible aquí
                            saved.getGroupScheduleId(),
                            saved.getDayOfWeek().name(),
                            saved.getStartTime(),
                            saved.getEndTime(),
                            saved.getClassroom()
                    )
            );
        }

        return new UpdateGroupScheduleResponse(
                true,
                "Los horarios del grupo fueron actualizados correctamente.",
                scheduleInfos
        );
    }
}