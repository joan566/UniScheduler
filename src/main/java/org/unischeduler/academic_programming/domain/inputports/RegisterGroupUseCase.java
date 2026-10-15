package org.unischeduler.academic_programming.domain.inputports;

import org.unischeduler.academic_programming.application.services.in.groups.RegisterGroupCommand;
import org.unischeduler.academic_programming.application.services.in.groups.dtos.RegisterGroupResponse;

public interface RegisterGroupUseCase {
    RegisterGroupResponse execute(RegisterGroupCommand command);
}
