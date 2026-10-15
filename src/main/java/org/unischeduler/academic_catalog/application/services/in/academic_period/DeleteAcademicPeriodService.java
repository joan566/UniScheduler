package org.unischeduler.academic_catalog.application.services.in.academic_period;

import org.unischeduler.academic_catalog.application.services.in.academic_period.dtos.DeleteAcademicPeriodResponse;
import org.unischeduler.academic_catalog.domain.inputports.academic_period.DeleteAcademicPeriodUseCase;
import org.unischeduler.academic_catalog.domain.outputports.AcademicPeriodRepository;

public class DeleteAcademicPeriodService implements DeleteAcademicPeriodUseCase {
    private final AcademicPeriodRepository academicPeriodRepository;

    public DeleteAcademicPeriodService(AcademicPeriodRepository academicPeriodRepository) {
        this.academicPeriodRepository = academicPeriodRepository;
    }

    @Override
    public DeleteAcademicPeriodResponse execute(DeleteAcademicPeriodCommand command) {
        if(!academicPeriodRepository.deleteById(command.getId())) {
            return new DeleteAcademicPeriodResponse(
                    false,
                    "No existe un periodo academico con id " + command.getId()
            );
        }

        return new DeleteAcademicPeriodResponse(
                true,
                "Se elimino el periodo academico con exito"
        );
    }
}
