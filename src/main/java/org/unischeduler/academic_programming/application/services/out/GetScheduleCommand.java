package org.unischeduler.academic_programming.application.services.out;

public class GetScheduleCommand {
    private final String studentId;

    public GetScheduleCommand(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentId() {
        return studentId;
    }
}
