package org.unischeduler.backend.infrastructure.mapper.academic_catalog;

import org.unischeduler.backend.domain.model.academic_catalog.entity.AcademicProgram;
import org.unischeduler.backend.infrastructure.entity.academic_catalog.AcademicProgramEntity;

public class AcademicProgramMapper {
    public static AcademicProgram toDomain(AcademicProgramEntity entity) {
        return new AcademicProgram(
                entity.getAcademicProgramId(),
                entity.getName(),
                entity.getTotalSemesters()
        );
    }

    public static AcademicProgramEntity toEntity(AcademicProgram domain) {
        AcademicProgramEntity entity = new AcademicProgramEntity();
        entity.setAcademicProgramId(domain.getAcademicProgramId());
        entity.setName(domain.getName());
        entity.setTotalSemesters(domain.getTotalSemesters());

        return entity;
    }
}
