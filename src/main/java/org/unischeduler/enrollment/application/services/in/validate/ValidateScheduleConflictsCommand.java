package org.unischeduler.enrollment.application.services.in.validate;

import org.unischeduler.academic_programming.application.services.out.dtos.GroupScheduleInfo;

import java.util.List;

public class ValidateScheduleConflictsCommand {
    private final Long studentId;
    private final List<GroupScheduleInfo> groupInfos;

    public ValidateScheduleConflictsCommand(Long studentId, List<GroupScheduleInfo> groupInfos) {
        this.studentId = studentId;
        this.groupInfos = groupInfos;
    }

    public Long getStudentId() {
        return studentId;
    }

    public List<GroupScheduleInfo> getGroupInfos() {
        return groupInfos;
    }
}
