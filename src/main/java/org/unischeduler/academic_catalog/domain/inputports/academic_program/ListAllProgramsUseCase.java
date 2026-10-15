package org.unischeduler.academic_catalog.domain.inputports.academic_program;

import org.unischeduler.academic_catalog.application.services.out.academic_programs.dtos.ListAllProgramsResponse;

public interface ListAllProgramsUseCase {
    ListAllProgramsResponse execute();
}
