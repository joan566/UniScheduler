package org.unischeduler.academic_catalog.domain.outputports;


import org.unischeduler.academic_catalog.domain.entity.AcademicPeriod;

import java.util.List;
import java.util.Optional;

public interface AcademicPeriodRepository {
    Optional<AcademicPeriod> findById(Long id);
    List<AcademicPeriod> findAll();
    boolean existsByCode(String code);
    Optional<AcademicPeriod> findByCode(String code);
    AcademicPeriod save(AcademicPeriod academicPeriod);
    void deleteById(Long id);
    Optional<AcademicPeriod> findActive();
}
