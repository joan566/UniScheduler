package org.unischeduler.academic_history.infrastructure.entity;

import java.util.List;

public class AcademicHistoryEntity {

    private Long historyId;
    private Long studentId;
    private List<Long> completedCoursesId;
    private double grade;
    private String status;

    public AcademicHistoryEntity() {}

    public AcademicHistoryEntity(
            Long historyId,
            Long studentId,
            List<Long> completedCoursesId,
            double grade,
            String status
    ) {
        this.historyId = historyId;
        this.studentId = studentId;
        this.completedCoursesId = completedCoursesId;
        this.grade = grade;
        this.status = status;
    }

    public Long getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Long historyId) {
        this.historyId = historyId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public List<Long> getCompletedCoursesId() {
        return completedCoursesId;
    }

    public void setCompletedCourses(List<Long> completedCourseId) {
        this.completedCoursesId = completedCourseId;
    }

    public void setCompletedCoursesId(List<Long> completedCoursesId) {
        this.completedCoursesId = completedCoursesId;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
