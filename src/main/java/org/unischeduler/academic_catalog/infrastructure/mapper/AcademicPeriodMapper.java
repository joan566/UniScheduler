package org.unischeduler.academic_catalog.infrastructure.mapper;


import org.unischeduler.academic_catalog.domain.entity.AcademicPeriod;
import org.unischeduler.academic_catalog.domain.enums.AcademicPeriodStatus;
import org.unischeduler.academic_catalog.infrastructure.entity.AcademicPeriodEntity;

import java.time.LocalDate;

public class AcademicPeriodMapper {

    public static AcademicPeriod toDomain(AcademicPeriodEntity entity) {

        if (entity == null) {
            return null;
        }

        return new AcademicPeriod(
                entity.getId(),
                entity.getCode(),
                entity.getName(),
                LocalDate.parse(entity.getStartDate()),
                LocalDate.parse(entity.getEndDate()),
                entity.getStatus()
        );
    }

    public static AcademicPeriodEntity toEntity(AcademicPeriod academicPeriod) {

        if (academicPeriod == null) {
            return null;
        }

        AcademicPeriodEntity entity = new AcademicPeriodEntity();

        entity.setId(academicPeriod.getId());
        entity.setCode(academicPeriod.getCode());
        entity.setName(academicPeriod.getName());
        entity.setStartDate(academicPeriod.getStartDate().toString());
        entity.setEndDate(academicPeriod.getEndDate().toString());
        entity.setStatus(academicPeriod.getStatus());

        return entity;
    }
}
