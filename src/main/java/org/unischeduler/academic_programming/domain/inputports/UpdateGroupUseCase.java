package org.unischeduler.academic_programming.domain.inputports;

import org.unischeduler.academic_programming.application.services.in.groups.UpdateGroupCommand;
import org.unischeduler.academic_programming.application.services.in.groups.dtos.UpdateGroupResponse;

public interface UpdateGroupUseCase {
    UpdateGroupResponse execute(UpdateGroupCommand command);
}
