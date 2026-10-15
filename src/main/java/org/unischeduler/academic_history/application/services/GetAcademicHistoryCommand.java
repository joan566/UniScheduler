package org.unischeduler.academic_history.application.services;

public class GetAcademicHistoryCommand {
    private final String studentId;

    public GetAcademicHistoryCommand(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentId() {
        return studentId;
    }
}
