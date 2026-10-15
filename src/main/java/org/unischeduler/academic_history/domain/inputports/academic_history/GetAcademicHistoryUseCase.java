package org.unischeduler.academic_history.domain.inputports.academic_history;

import org.unischeduler.academic_history.application.services.GetAcademicHistoryCommand;
import org.unischeduler.academic_history.application.services.GetAcademicHistoryResponse;

public interface GetAcademicHistoryUseCase {
    GetAcademicHistoryResponse execute(GetAcademicHistoryCommand command);
}
