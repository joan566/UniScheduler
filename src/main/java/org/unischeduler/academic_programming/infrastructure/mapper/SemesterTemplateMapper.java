package org.unischeduler.academic_programming.infrastructure.mapper;

import org.unischeduler.academic_catalog.domain.entity.AcademicProgram;
import org.unischeduler.academic_programming.domain.entity.SemesterTemplate;
import org.unischeduler.academic_programming.domain.entity.SemesterTemplateDetail;
import org.unischeduler.academic_programming.infrastructure.entity.SemesterTemplateEntity;

import java.util.List;

public class SemesterTemplateMapper {

    public static SemesterTemplateEntity toEntity(
            SemesterTemplate template
    ) {
        SemesterTemplateEntity entity = new SemesterTemplateEntity();

        entity.setTemplateId(
                template.getTemplateId()
        );

        entity.setProgramId(
                template.getAcademicProgram().getAcademicProgramId()
        );

        entity.setSemester(
                template.getSemester()
        );

        return entity;
    }

    public static SemesterTemplate toDomain(
            SemesterTemplateEntity entity,
            AcademicProgram academicProgram,
            List<SemesterTemplateDetail> details
    ) {
        return new SemesterTemplate(
                entity.getTemplateId(),
                academicProgram,
                entity.getSemester(),
                details
        );
    }
}
