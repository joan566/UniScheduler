package org.unischeduler.academic_programming.domain.entity;

public class Teacher {

    //================// Atributos //================//
    private Long id;
    private String name;

    //================// Constructores //================//

    public Teacher(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    //================// Funciones Adicionales //================//



}
