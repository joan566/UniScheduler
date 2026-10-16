package org.unischeduler.academic_programming.application.services.in.group_schedules;

public class DeleteGroupSchedulesCommand {
    private final Long groupId;

    public DeleteGroupSchedulesCommand(Long groupId) {
        this.groupId = groupId;
    }

    public Long getGroupId() {
        return groupId;
    }
}
