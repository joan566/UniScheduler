package org.unischeduler.academic_catalog.infrastructure.mapper;

import org.unischeduler.academic_catalog.domain.entity.AcademicProgram;
import org.unischeduler.academic_catalog.infrastructure.entity.AcademicProgramEntity;

public class AcademicProgramMapper {
    public static AcademicProgram toDomain(AcademicProgramEntity entity) {
        return new AcademicProgram(
                entity.getId(),
                entity.getName(),
                entity.getTotalSemesters()
        );
    }

    public static AcademicProgramEntity toEntity(AcademicProgram domain) {
        AcademicProgramEntity entity = new AcademicProgramEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setTotalSemesters(domain.getTotalSemesters());

        return entity;
    }
}
