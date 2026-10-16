package org.unischeduler.academic_catalog.domain.entity;

public class AcademicProgram {

    //================// Atributos //================//
    private Long academicProgramId;
    private String name;
    private int totalSemesters;

    //================// Constructores //================//

    public AcademicProgram() {
    }

    public AcademicProgram(Long academicProgramId, String name, int totalSemesters) {
        this.academicProgramId = academicProgramId;
        this.name = name;
        this.totalSemesters = totalSemesters;
    }

    //================// Setters y Getters //================//

    public Long getAcademicProgramId() {
        return academicProgramId;
    }

    public void setAcademicProgramId(Long academicProgramId) {
        this.academicProgramId = academicProgramId;
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
