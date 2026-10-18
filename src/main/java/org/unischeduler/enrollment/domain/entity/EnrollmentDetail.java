package org.unischeduler.enrollment.domain.entity;

import org.unischeduler.academic_programming.domain.entity.Group;
import org.unischeduler.enrollment.domain.enums.EnrollmentStatus;

public class EnrollmentDetail {
    private final Long id;
    private Group group;

    private EnrollmentStatus status;
    private Double finalGrade;

    //================// Constructores //================//

    public EnrollmentDetail(Long id, Group group, EnrollmentStatus status, Double finalGrade) {
        this.id = id;
        this.group = group;
        this.status = status;
        this.finalGrade = finalGrade;
    }

    public Long getId() {
        return id;
    }

    public Group getGroup() {
        return group;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    public Double getFinalGrade() {
        return finalGrade;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setStatus(EnrollmentStatus status) {
        this.status = status;
    }

    public void setFinalGrade(Double finalGrade) {
        this.finalGrade = finalGrade;
    }
}
