package org.unischeduler.academic_catalog.application.services.out.academic_period.dtos;



import org.unischeduler.academic_catalog.application.services.in.academic_period.dtos.AcademicPeriodInfo;

import java.util.List;

public class ListAllAcademicPeriodsResponse {
    private final boolean succssesfully;
    private final String messge;
    private final List<AcademicPeriodInfo> academicPeriods;

    public ListAllAcademicPeriodsResponse(boolean succssesfully, String messge, List<AcademicPeriodInfo> academicPeriods) {
        this.succssesfully = succssesfully;
        this.messge = messge;
        this.academicPeriods = academicPeriods;
    }

    public boolean isSuccssesfully() {
        return succssesfully;
    }

    public String getMessge() {
        return messge;
    }

    public List<AcademicPeriodInfo> getAcademicPeriods() {
        return academicPeriods;
    }
}
