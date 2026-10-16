package org.unischeduler.enrollment.application.services.in.validate;

import java.util.List;

public class ValidatePrerequisiteCommand {
    private final Long studentId;
    private final List<Long> courseIds;

    public ValidatePrerequisiteCommand(Long studentId, List<Long> courseIds) {
        this.studentId = studentId;
        this.courseIds = courseIds;
    }

    public Long getStudentId() {
        return studentId;
    }

    public List<Long> getCourseIds() {
        return courseIds;
    }
}
