package org.unischeduler.academic_catalog.infrastructure.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unischeduler.academic_catalog.infrastructure.entity.AcademicProgramEntity;

public interface SpringAcademicProgramRepository extends JpaRepository<AcademicProgramEntity, Long> {
}
