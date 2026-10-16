package org.unischeduler.academic_history.domain.outputports;

import org.unischeduler.academic_history.domain.entity.AcademicHistory;

import java.util.Optional;

public interface AcademicHistoryRepository {
    Optional<AcademicHistory> findByStudentId(Long studentId);
    AcademicHistory save(AcademicHistory academicHistory);
}
