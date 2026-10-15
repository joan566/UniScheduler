package org.unischeduler.academic_programming.domain.outputports;

import org.unischeduler.academic_catalog.domain.entity.AcademicProgram;
import org.unischeduler.academic_programming.domain.entity.SemesterTemplate;

public interface SemesterTemplateRepository {
    SemesterTemplate findByProgramAndSemester(AcademicProgram program, int semester);
}
