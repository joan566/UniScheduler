package org.unischeduler.academic_catalog.domain.inputports.academic_period;

import org.unischeduler.academic_catalog.application.services.out.academic_period.dtos.ListAllAcademicPeriodsResponse;

public interface ListAllAcademicPeriodsUseCase {
    ListAllAcademicPeriodsResponse execute();
}
