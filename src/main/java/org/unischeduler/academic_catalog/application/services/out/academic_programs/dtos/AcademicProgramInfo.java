package org.unischeduler.academic_catalog.application.services.out.academic_programs.dtos;

import org.unischeduler.academic_catalog.domain.entity.AcademicProgram;

public class AcademicProgramInfo {
    private final Long academicProgramId;
    private final String name;
    private final int totalSemesters;

    public AcademicProgramInfo(Long academicProgramId, String name, int totalSemesters) {
        this.academicProgramId = academicProgramId;
        this.name = name;
        this.totalSemesters = totalSemesters;
    }

    public Long getAcademicProgramId() {
        return academicProgramId;
    }

    public String getName() {
        return name;
    }

    public int getTotalSemesters() {
        return totalSemesters;
    }

     public static AcademicProgramInfo fromDomain(AcademicProgram program) {
        return new AcademicProgramInfo(
                program.getId(),
                program.getName(),
                program.getTotalSemesters()
        );
    }
}
