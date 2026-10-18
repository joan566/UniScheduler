package org.unischeduler.academic_catalog.application.services.in.academic_period;

import org.unischeduler.academic_catalog.application.services.in.academic_period.dtos.DeleteAcademicPeriodResponse;
import org.unischeduler.academic_catalog.domain.entity.AcademicPeriod;
import org.unischeduler.academic_catalog.domain.inputports.academic_period.DeleteAcademicPeriodUseCase;
import org.unischeduler.academic_catalog.domain.outputports.AcademicPeriodRepository;
import org.unischeduler.shared.domain.exceptions.EntityNotFoundException;

public class DeleteAcademicPeriodService implements DeleteAcademicPeriodUseCase {
    private final AcademicPeriodRepository academicPeriodRepository;

    public DeleteAcademicPeriodService(AcademicPeriodRepository academicPeriodRepository) {
        this.academicPeriodRepository = academicPeriodRepository;
    }

    @Override
    public DeleteAcademicPeriodResponse execute(DeleteAcademicPeriodCommand command) {
        AcademicPeriod academicPeriod = academicPeriodRepository.findById(command.getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "No se encontro un periodo academico con id: " + command.getId()
                ));

        academicPeriodRepository.deleteById(academicPeriod.getAcademicPeriodId());

        return new DeleteAcademicPeriodResponse(
                true,
                "Se elimino el periodo academico con exito"
        );
    }
}
