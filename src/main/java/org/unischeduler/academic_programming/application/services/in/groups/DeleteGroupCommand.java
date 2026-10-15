package org.unischeduler.academic_programming.application.services.in.groups;

public class DeleteGroupCommand {
    private final String groupId;

    public DeleteGroupCommand(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupId() {
        return groupId;
    }
}
