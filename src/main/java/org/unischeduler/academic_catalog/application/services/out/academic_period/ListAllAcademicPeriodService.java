package org.unischeduler.academic_catalog.application.services.out.academic_period;

import org.unischeduler.academic_catalog.application.services.in.academic_period.dtos.AcademicPeriodInfo;
import org.unischeduler.academic_catalog.domain.entity.AcademicPeriod;
import org.unischeduler.academic_catalog.domain.inputports.academic_period.ListAllAcademicPeriodsUseCase;
import org.unischeduler.academic_catalog.application.services.out.academic_period.dtos.ListAllAcademicPeriodsResponse;
import org.unischeduler.academic_catalog.domain.outputports.AcademicPeriodRepository;

import java.util.List;

public class ListAllAcademicPeriodService implements ListAllAcademicPeriodsUseCase {
    private final AcademicPeriodRepository academicPeriodRepository;

    public ListAllAcademicPeriodService(AcademicPeriodRepository academicPeriodRepository) {
        this.academicPeriodRepository = academicPeriodRepository;
    }

    @Override
    public ListAllAcademicPeriodsResponse execute() {
        List<AcademicPeriod> academicPeriods = academicPeriodRepository.findAll();

        List<AcademicPeriodInfo> academicPeriodInfos = academicPeriods
                .stream()
                .map(AcademicPeriodInfo::fromDomain)
                .toList();

        return new ListAllAcademicPeriodsResponse(
                true,
                "Se encontraron " + academicPeriodInfos.size() + " periodos academicos",
                academicPeriodInfos
        );
    }
}
