package org.unischeduler.academic_catalog.domain.entity;

public class Prerequisite {

    //================// Atributos //================//
    private Long id;
    private Long courseId;
    private Course requiredCourse;


    //================// Constructores //================//

    public Prerequisite(Long id, Long courseId, Course requiredCourse) {
        this.id = id;
        this.courseId = courseId;
        this.requiredCourse = requiredCourse;
    }

    //================// Setters y Getters //================//

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
