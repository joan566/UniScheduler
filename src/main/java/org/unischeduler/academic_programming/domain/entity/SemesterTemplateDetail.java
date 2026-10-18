package org.unischeduler.academic_programming.domain.entity;

import org.unischeduler.academic_catalog.domain.entity.Course;

public class SemesterTemplateDetail {
    private Long id;
    private Course course;

    public SemesterTemplateDetail(Long id, Course course) {
        this.id = id;
        this.course = course;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
