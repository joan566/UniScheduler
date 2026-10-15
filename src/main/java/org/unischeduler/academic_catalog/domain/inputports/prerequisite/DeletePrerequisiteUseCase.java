package org.unischeduler.academic_catalog.domain.inputports.prerequisite;

import org.unischeduler.academic_catalog.application.services.in.prerequisite.DeletePrerequisiteCommand;

public interface DeletePrerequisiteUseCase {
    void execute(DeletePrerequisiteCommand command);
}