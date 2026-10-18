package org.unischeduler.academic_catalog.infrastructure.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unischeduler.academic_catalog.infrastructure.entity.CourseEntity;

import java.util.Optional;

public interface SpringCourseRepository extends JpaRepository<CourseEntity, Long> {
    boolean existsByCode(String code);
    Optional<CourseEntity> findByCode(String code);
    void deleteById(Long id);
}
