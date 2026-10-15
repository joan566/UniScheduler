package org.unischeduler.academic_catalog.domain.inputports.academic_period;

import org.unischeduler.academic_catalog.application.services.in.academic_period.DeleteAcademicPeriodCommand;
import org.unischeduler.academic_catalog.application.services.in.academic_period.dtos.DeleteAcademicPeriodResponse;

public interface DeleteAcademicPeriodUseCase {
    DeleteAcademicPeriodResponse execute(DeleteAcademicPeriodCommand command);
}
