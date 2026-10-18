package org.unischeduler.enrollment.infrastructure.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unischeduler.enrollment.infrastructure.entity.EnrollmentDetailEntity;

import java.util.List;

public interface SpringEnrollmentDetailRepository extends JpaRepository<EnrollmentDetailEntity, Long> {
    List<EnrollmentDetailEntity> findAllByEnrollmentId(Long enrollmentId);
}
