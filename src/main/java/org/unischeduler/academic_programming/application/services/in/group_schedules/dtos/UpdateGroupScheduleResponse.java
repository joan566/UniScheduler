package org.unischeduler.academic_programming.application.services.in.group_schedules.dtos;

import org.unischeduler.academic_programming.application.services.out.dtos.GroupScheduleInfo;
import org.unischeduler.academic_programming.domain.entity.GroupSchedule;

import java.util.List;

public class UpdateGroupScheduleResponse {
    private final boolean successfully;
    private final String message;
    private final List<GroupScheduleInfo> groupSchedules;

    public UpdateGroupScheduleResponse(boolean successfully, String message, List<GroupScheduleInfo> groupSchedules) {
        this.successfully = successfully;
        this.message = message;
        this.groupSchedules = groupSchedules;
    }

    public boolean isSuccessfully() {
        return successfully;
    }

    public String getMessage() {
        return message;
    }

    public List<GroupScheduleInfo> getGroupSchedules() {
        return groupSchedules;
    }
}
