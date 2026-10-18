package org.unischeduler.academic_programming.domain.entity;

import org.unischeduler.academic_catalog.domain.entity.AcademicProgram;

import java.util.List;

public class SemesterTemplate {
    private Long id;
    private AcademicProgram academicProgram;
    private int semester;
    private List<SemesterTemplateDetail> details;

    public SemesterTemplate(Long id, AcademicProgram academicProgram, int semester, List<SemesterTemplateDetail> details) {
        this.id = id;
        this.academicProgram = academicProgram;
        this.semester = semester;
        this.details = details;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AcademicProgram getAcademicProgram() {
        return academicProgram;
    }

    public void setAcademicProgram(AcademicProgram academicProgram) {
        this.academicProgram = academicProgram;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public List<SemesterTemplateDetail> getDetails() {
        return details;
    }

    public void setDetails(List<SemesterTemplateDetail> details) {
        this.details = details;
    }
}
