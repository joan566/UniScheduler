package org.unischeduler.academic_catalog.infrastructure.entity;

public class AcademicProgramEntity {
    private String academicProgramId;
    private String name;
    private int totalSemesters;

    public String getAcademicProgramId() {
        return academicProgramId;
    }

    public void setAcademicProgramId(String academicProgramId) {
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

    public void setTotalSemesters(int totalSemesters) {
        this.totalSemesters = totalSemesters;
    }
}
