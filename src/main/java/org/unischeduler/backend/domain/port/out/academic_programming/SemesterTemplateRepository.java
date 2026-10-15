package org.unischeduler.backend.domain.port.out.academic_programming;

import org.unischeduler.academic_catalog.domain.entity.AcademicProgram;
import org.unischeduler.backend.domain.model.academic_programming.entity.SemesterTemplate;

public interface SemesterTemplateRepository {
    SemesterTemplate findByProgramAndSemester(AcademicProgram program, int semester);
}
