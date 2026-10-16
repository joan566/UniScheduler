package org.unischeduler.enrollment.application.services.in.register.dtos;

public class StudentInfo {
    private final Long userId;
    private final Long studentId;
    private final String studentCode;
    private final String studentPassword;

    public StudentInfo(Long userId, Long studentId, String studentCode, String studentPassword) {
        this.userId = userId;
        this.studentId = studentId;
        this.studentCode = studentCode;
        this.studentPassword = studentPassword;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public String getStudentPassword() {
        return studentPassword;
    }
}
