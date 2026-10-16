package org.unischeduler.enrollment.infrastructure.entity;

public class StudentEntity {
    private Long studentId;
    private String studentCode;
    private Long userId;
    private Long academicProgramId;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAcademicProgramId() {
        return academicProgramId;
    }

    public void setAcademicProgramId(Long academicProgramId) {
        this.academicProgramId = academicProgramId;
    }
}
