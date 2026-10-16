package org.unischeduler.academic_programming.application.services.out;

public class GetScheduleCommand {
    private final Long studentId;

    public GetScheduleCommand(Long studentId) {
        this.studentId = studentId;
    }

    public Long getStudentId() {
        return studentId;
    }
}
