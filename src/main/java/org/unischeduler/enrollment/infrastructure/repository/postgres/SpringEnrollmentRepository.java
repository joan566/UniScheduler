package org.unischeduler.enrollment.infrastructure.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unischeduler.enrollment.infrastructure.entity.EnrollmentEntity;

import java.util.List;
import java.util.Optional;

public interface SpringEnrollmentRepository extends JpaRepository<EnrollmentEntity, Long> {
    Optional<EnrollmentEntity> findByStudentIdAndAcademicPeriodId(Long studentId, Long academicPeriodId);

    List<EnrollmentEntity> findAllByStudentId(Long studentId);
}
