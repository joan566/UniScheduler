package org.unischeduler.enrollment.infrastructure.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unischeduler.enrollment.infrastructure.entity.StudentEntity;

import java.util.Optional;

public interface SpringStudentRepository extends JpaRepository<StudentEntity, Long> {
    boolean existsByStudentCode(String studentCode);

    Optional<StudentEntity> findByUserId(Long userId);
}
