package org.unischeduler.academic_catalog.domain.entity;

public class Prerequisite {

    //================// Atributos //================//
    private Long prerequisite;
    private Long courseId;
    private Course requiredCourse;


    //================// Constructores //================//

    public Prerequisite(Long prerequisite, Long courseId, Course requiredCourse) {
        this.prerequisite = prerequisite;
        this.courseId = courseId;
        this.requiredCourse = requiredCourse;
    }

    //================// Setters y Getters //================//

    public Long getPrerequisite() {
        return prerequisite;
    }

    public void setPrerequisite(Long prerequisite) {
        this.prerequisite = prerequisite;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Course getRequiredCourse() {
        return requiredCourse;
    }

    public void setRequiredCourse(Course requiredCourse) {
        this.requiredCourse = requiredCourse;
    }


    //================// Funciones Adicionales //================//
}
