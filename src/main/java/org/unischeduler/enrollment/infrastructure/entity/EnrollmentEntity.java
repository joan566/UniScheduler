package org.unischeduler.enrollment.infrastructure.entity;


import java.time.LocalDate;

public class EnrollmentEntity {
    private Long enrollmentId;
    private Long studentId;
    private Long academicProgramId;
    private LocalDate enrollmentDate;
    private Long academicPeriodId;

    public Long getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(Long enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getAcademicProgramId() {
        return academicProgramId;
    }

    public void setAcademicProgramId(Long academicProgramId) {
        this.academicProgramId = academicProgramId;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public Long getAcademicPeriodId() {
        return academicPeriodId;
    }

    public void setAcademicPeriodId(Long academicPeriodId) {
        this.academicPeriodId = academicPeriodId;
    }
}
