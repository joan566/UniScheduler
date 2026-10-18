package org.unischeduler.academic_programming.infrastructure.mapper;

import org.unischeduler.academic_catalog.domain.entity.Course;
import org.unischeduler.academic_programming.domain.entity.SemesterTemplateDetail;
import org.unischeduler.academic_programming.infrastructure.entity.SemesterTemplateDetailEntity;

public class SemesterTemplateDetailMapper {

    public static SemesterTemplateDetailEntity toEntity(
            SemesterTemplateDetail detail,
            Long templateId
    ) {
        SemesterTemplateDetailEntity entity =
                new SemesterTemplateDetailEntity();

        entity.setId(
                detail.getId()
        );

        entity.setTemplateId(templateId);

        entity.setCourseId(
                detail.getCourse().getId()
        );

        return entity;
    }

    public static SemesterTemplateDetail toDomain(
            SemesterTemplateDetailEntity entity,
            Course course
    ) {
        return new SemesterTemplateDetail(
                entity.getId(),
                course
        );
    }
}
