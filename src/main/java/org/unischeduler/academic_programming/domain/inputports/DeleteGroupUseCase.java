package org.unischeduler.academic_programming.domain.inputports;

import org.unischeduler.academic_programming.application.services.in.groups.DeleteGroupCommand;
import org.unischeduler.academic_programming.application.services.in.groups.dtos.DeleteGroupResponse;

public interface DeleteGroupUseCase {
    DeleteGroupResponse execute(DeleteGroupCommand command);
}
