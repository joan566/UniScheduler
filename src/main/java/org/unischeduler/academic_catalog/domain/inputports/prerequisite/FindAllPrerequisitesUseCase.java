package org.unischeduler.academic_catalog.domain.inputports.prerequisite;

import org.unischeduler.academic_catalog.application.services.in.prerequisite.FindAllPrerequisitesCommand;
import org.unischeduler.academic_catalog.application.services.in.prerequisite.dtos.FindAllPrerequisitesResponse;

public interface FindAllPrerequisitesUseCase {
    FindAllPrerequisitesResponse execute(FindAllPrerequisitesCommand command);
}
