package org.unischeduler.academic_catalog.domain.inputports.academic_period;


import org.unischeduler.academic_catalog.application.services.in.academic_period.UpdateAcademicPeriodCommand;
import org.unischeduler.academic_catalog.application.services.in.academic_period.dtos.UpdateAcademicPeriodResponse;

public interface UpdateAcademicPeriodUseCase {
    UpdateAcademicPeriodResponse execute(UpdateAcademicPeriodCommand command);
}
