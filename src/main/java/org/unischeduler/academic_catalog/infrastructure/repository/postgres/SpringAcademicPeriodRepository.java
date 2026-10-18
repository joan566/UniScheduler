package org.unischeduler.academic_catalog.infrastructure.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.unischeduler.academic_catalog.infrastructure.entity.AcademicPeriodEntity;

import java.util.Optional;

public interface SpringAcademicPeriodRepository extends JpaRepository<AcademicPeriodEntity, Long> {
    Optional<AcademicPeriodEntity> findByCode(String code);
    boolean existsByCode(String code);
    void deleteById(Long id);

    @Query(value = """
            SELECT *
            FROM academic_periods
            WHERE status = 'ACTIVE'
            """, nativeQuery = true)
    Optional<AcademicPeriodEntity> findActive();
}
