package org.unischeduler.academic_catalog.domain.entity;

public class AcademicProgram {

    //================// Atributos //================//
    private Long id;
    private String name;
    private int totalSemesters;

    //================// Constructores //================//

    public AcademicProgram() {
    }

    public AcademicProgram(Long id, String name, int totalSemesters) {
        this.id = id;
        this.name = name;
        this.totalSemesters = totalSemesters;
    }

    //================// Setters y Getters //================//

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalSemesters() {
        return totalSemesters;
    }

    //================// Funciones Adicionales //================//


}
