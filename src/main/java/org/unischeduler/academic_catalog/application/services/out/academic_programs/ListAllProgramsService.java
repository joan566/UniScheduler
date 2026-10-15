package org.unischeduler.academic_catalog.application.services.out.academic_programs;

import org.unischeduler.academic_catalog.application.services.out.academic_programs.dtos.AcademicProgramInfo;
import org.unischeduler.academic_catalog.application.services.out.academic_programs.dtos.ListAllProgramsResponse;
import org.unischeduler.academic_catalog.domain.inputports.academic_program.ListAllProgramsUseCase;
import org.unischeduler.academic_catalog.domain.outputports.AcademicProgramRepository;

import java.util.List;

public class ListAllProgramsService implements ListAllProgramsUseCase {
    private final AcademicProgramRepository programRepository;

    public ListAllProgramsService(AcademicProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    @Override
    public ListAllProgramsResponse execute() {
        List<AcademicProgramInfo> programsInfo = programRepository.findAll()
                .stream()
                .map(AcademicProgramInfo::fromDomain)
                .toList();

        return new ListAllProgramsResponse(
                true,
                "Se encontraron " + programsInfo.size() + " programas academicos",
                programsInfo
        );


    }
}
