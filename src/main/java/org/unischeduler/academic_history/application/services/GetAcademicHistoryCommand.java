package org.unischeduler.academic_history.application.services;

public class GetAcademicHistoryCommand {
    private final Long studentId;

    public GetAcademicHistoryCommand(Long studentId) {
        this.studentId = studentId;
    }

    public Long getStudentId() {
        return studentId;
    }
}
