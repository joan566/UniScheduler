package org.unischeduler.backend.infrastructure.persistence.excel.repository.academic_program;

import org.unischeduler.academic_catalog.infrastructure.entity.AcademicProgramEntity;
import org.unischeduler.backend.infrastructure.entity.academic_programming.SemesterTemplateEntity;
import org.unischeduler.backend.infrastructure.persistence.excel.core.ExcelDataStore;

public class ExcelSemesterTemplateRepository {

    private final ExcelDataStore store;

    public ExcelSemesterTemplateRepository(ExcelDataStore store) {
        this.store = store;
    }

    // =====================================================
    // 🔍 FIND BY PROGRAM + SEMESTER
    // =====================================================
    public SemesterTemplateEntity findByProgramAndSemester(AcademicProgramEntity program, int semester) {

        String programId = program.getAcademicProgramId();

        for (SemesterTemplateEntity t : store.getTemplates().values()) {

            if (programId.equals(t.getProgramId()) && t.getSemester() == semester) {
                return t;
            }
        }

        return null;
    }
}