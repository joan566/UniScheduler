package org.unischeduler.academic_programming.application.services.in.groups;

public class DeleteGroupCommand {
    private final Long groupId;

    public DeleteGroupCommand(Long groupId) {
        this.groupId = groupId;
    }

    public Long getGroupId() {
        return groupId;
    }
}
