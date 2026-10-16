package org.unischeduler.academic_catalog.domain.entity;

import java.util.ArrayList;
import java.util.List;

public class Course {

    //================// Atributos //================//
    private Long courseId;
    private String name;
    private String code;
    private int credits;
    private String description;

    private List<Course> prerequisites;


    public Course(Long courseId, String name, String code, int credits, String description, List<Course> prerequisites) {
        this.courseId = courseId;
        this.name = name;
        this.code = code;
        this.credits = credits;
        this.description = description;
        this.prerequisites = prerequisites != null? prerequisites : new ArrayList<>();
    }

    //================// Setters y Getters //================//

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Course> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(List<Course> prerequisites) {
        this.prerequisites = prerequisites;
    }


    //================// Funciones Adicionales //================//
}
