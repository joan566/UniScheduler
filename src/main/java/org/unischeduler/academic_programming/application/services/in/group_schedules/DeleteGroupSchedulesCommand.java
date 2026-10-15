package org.unischeduler.academic_programming.application.services.in.group_schedules;

public class DeleteGroupSchedulesCommand {
    private final String groupId;

    public DeleteGroupSchedulesCommand(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupId() {
        return groupId;
    }
}
