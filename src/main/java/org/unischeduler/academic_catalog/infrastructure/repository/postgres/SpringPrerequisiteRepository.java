package org.unischeduler.academic_catalog.infrastructure.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unischeduler.academic_catalog.infrastructure.entity.PrerequisiteEntity;

import java.util.List;

public interface SpringPrerequisiteRepository extends JpaRepository<PrerequisiteEntity, Long> {
    List<PrerequisiteEntity> findAllPrerequisitesWhereCourseId(Long courseId);
    boolean deleteWhereCourseId(Long id);
}
