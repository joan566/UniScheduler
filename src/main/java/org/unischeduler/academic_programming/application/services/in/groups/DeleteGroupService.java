package org.unischeduler.academic_programming.application.services.in.groups;

import org.unischeduler.academic_programming.application.services.in.groups.dtos.DeleteGroupResponse;
import org.unischeduler.academic_programming.domain.inputports.DeleteGroupUseCase;
import org.unischeduler.academic_programming.domain.outputports.GroupRepository;

public class DeleteGroupService implements DeleteGroupUseCase {
    private final GroupRepository groupRepository;

    public DeleteGroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public DeleteGroupResponse execute(DeleteGroupCommand command) {
        if(!groupRepository.deleteById(command.getGroupId())) {
            return new DeleteGroupResponse(
                    false,
                    "No existe el grupo con id " + command.getGroupId()
            );
        }

        return new DeleteGroupResponse(
                true,
                "Se elimino el grupo con exito"
        );
    }
}
