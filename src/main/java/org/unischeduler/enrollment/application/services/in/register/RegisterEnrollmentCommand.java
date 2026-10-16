package org.unischeduler.enrollment.application.services.in.register;

import java.util.List;

public class RegisterEnrollmentCommand {
    private final Long studentId;
    private final List<Long> groupIds;

    public RegisterEnrollmentCommand(Long studentId, List<Long> groupIds) {
        this.studentId = studentId;
        this.groupIds = groupIds;
    }

    public Long getStudentId() {
        return studentId;
    }

    public List<Long> getGroupIds() {
        return groupIds;
    }
}
