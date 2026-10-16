package org.unischeduler.enrollment.domain.outputports;

import org.unischeduler.enrollment.domain.entity.Enrollment;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository {
    Enrollment save(Enrollment enrollment);
    Optional<Enrollment> findByStudentAndActivePeriod(Long studentId);
    List<Enrollment> findAllWhereStudentId(Long studentId);
}
