package org.unischeduler.academic_catalog.domain.outputports;

import org.unischeduler.academic_catalog.domain.entity.AcademicProgram;

import java.util.List;
import java.util.Optional;

public interface AcademicProgramRepository {
    Optional<AcademicProgram> findById(Long id);
    List<AcademicProgram> findAll();
}
