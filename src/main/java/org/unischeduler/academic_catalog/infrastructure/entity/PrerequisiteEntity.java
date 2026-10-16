package org.unischeduler.academic_catalog.infrastructure.entity;

public class PrerequisiteEntity {
    private Long prerequisiteId;
    private Long courseId;
    private Long prerequisiteCourseId;

    public Long getPrerequisiteId() {
        return prerequisiteId;
    }

    public void setPrerequisiteId(Long prerequisiteId) {
        this.prerequisiteId = prerequisiteId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getPrerequisiteCourseId() {
        return prerequisiteCourseId;
    }

    public void setPrerequisiteCourseId(Long prerequisiteCourseId) {
        this.prerequisiteCourseId = prerequisiteCourseId;
    }
}
