package org.unischeduler.academic_programming.domain.entity;

public class Teacher {

    //================// Atributos //================//
    private Long teacherId;
    private String name;

    //================// Constructores //================//

    public Teacher(Long teacherId, String name) {
        this.teacherId = teacherId;
        this.name = name;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public String getName() {
        return name;
    }

    //================// Funciones Adicionales //================//



}
