package org.unischeduler.academic_programming.infrastructure.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unischeduler.academic_programming.infrastructure.entity.SemesterTemplateDetailEntity;

import java.util.List;

public interface SpringSemesterTemplateDetailRepository extends JpaRepository<SemesterTemplateDetailEntity, Long> {
    List<SemesterTemplateDetailEntity> findAllByTemplateId(Long templateId);
}
