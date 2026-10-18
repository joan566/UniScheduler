package org.unischeduler.academic_programming.infrastructure.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unischeduler.academic_programming.infrastructure.entity.TeacherEntity;

public interface SpringTeacherRepository extends JpaRepository<TeacherEntity, Long> {
}
