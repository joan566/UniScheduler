package org.unischeduler.enrollment.infrastructure.entity;

public class EnrollmentDetailEntity {
    private Long enrollmentDetailId;
    private Long enrollmentId;
    private Long groupId;
    private String status;
    private Double finalGrade;

    public Long getEnrollmentDetailId() {
        return enrollmentDetailId;
    }

    public void setEnrollmentDetailId(Long enrollmentDetailId) {
        this.enrollmentDetailId = enrollmentDetailId;
    }

    public Long getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(Long enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(Double finalGrade) {
        this.finalGrade = finalGrade;
    }
}
