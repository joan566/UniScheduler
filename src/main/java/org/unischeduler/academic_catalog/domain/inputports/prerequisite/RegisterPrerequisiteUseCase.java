package org.unischeduler.academic_catalog.domain.inputports.prerequisite;

import org.unischeduler.academic_catalog.application.services.in.prerequisite.RegisterPrerequisiteCommand;
import org.unischeduler.academic_catalog.application.services.in.prerequisite.dtos.RegisterPrerequisiteResponse;

public interface RegisterPrerequisiteUseCase {
    RegisterPrerequisiteResponse execute(RegisterPrerequisiteCommand command);
}
