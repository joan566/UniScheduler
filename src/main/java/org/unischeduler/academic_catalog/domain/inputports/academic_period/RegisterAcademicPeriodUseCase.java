package org.unischeduler.academic_catalog.domain.inputports.academic_period;

import org.unischeduler.academic_catalog.application.services.in.academic_period.RegisterAcademicPeriodCommand;
import org.unischeduler.academic_catalog.application.services.in.academic_period.dtos.RegisterAcademicPeriodResponse;

public interface RegisterAcademicPeriodUseCase {
    RegisterAcademicPeriodResponse execute(RegisterAcademicPeriodCommand command);
}
