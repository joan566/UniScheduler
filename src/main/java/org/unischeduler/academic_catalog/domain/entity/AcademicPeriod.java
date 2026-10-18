package org.unischeduler.academic_catalog.domain.entity;


import org.unischeduler.academic_catalog.domain.enums.AcademicPeriodStatus;

import java.time.LocalDate;

public class AcademicPeriod {

    //================// Atributos //================//
    private final Long id;
    private final String code;
    private String name;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private AcademicPeriodStatus status;

    //================// Constructores //================//


    public AcademicPeriod(Long id, String code, String name, LocalDate startDate, LocalDate endDate, AcademicPeriodStatus status) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public Long getId() {
        return id;
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

    public AcademicPeriodStatus getStatus() {
        return status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(AcademicPeriodStatus status) {
        this.status = status;
    }
}
