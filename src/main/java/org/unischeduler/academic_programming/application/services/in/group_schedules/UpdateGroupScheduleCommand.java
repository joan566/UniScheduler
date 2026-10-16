package org.unischeduler.academic_programming.application.services.in.group_schedules;

import org.unischeduler.academic_programming.domain.entity.GroupSchedule;

import java.util.List;

public class UpdateGroupScheduleCommand {
    private final Long groupId;
    private final List<GroupSchedule> schedules;

    public UpdateGroupScheduleCommand(Long groupId, List<GroupSchedule> schedules) {
        this.groupId = groupId;
        this.schedules = schedules;
    }

    public Long getGroupId() {
        return groupId;
    }

    public List<GroupSchedule> getSchedules() {
        return schedules;
    }
}
