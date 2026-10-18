package org.unischeduler.academic_programming.infrastructure.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unischeduler.academic_programming.infrastructure.entity.SemesterTemplateEntity;

import java.util.Optional;

public interface SpringSemesterTemplateRepository extends JpaRepository<SemesterTemplateEntity, Long> {
    Optional<SemesterTemplateEntity> findByProgramIdAndSemester(Long programId, int semester);
}
