package org.unischeduler.academic_catalog.application.services.in.academic_period.dtos;



import org.unischeduler.academic_catalog.domain.entity.AcademicPeriod;

import java.time.LocalDate;

public class AcademicPeriodInfo {
    private final Long academicPeriodId;
    private final String code;
    private final String name;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String status;

    public AcademicPeriodInfo(Long academicPeriodId, String code, String name, LocalDate startDate, LocalDate endDate, String status) {
        this.academicPeriodId = academicPeriodId;
        this.code = code;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public static AcademicPeriodInfo fromDomain(AcademicPeriod academicPeriod) {
        return new AcademicPeriodInfo(
                academicPeriod.getId(),
                academicPeriod.getCode(),
                academicPeriod.getName(),
                academicPeriod.getStartDate(),
                academicPeriod.getEndDate(),
                academicPeriod.getStatus().name()
        );
    }

    public Long getAcademicPeriodId() {
        return academicPeriodId;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getStatus() {
        return status;
    }
}
