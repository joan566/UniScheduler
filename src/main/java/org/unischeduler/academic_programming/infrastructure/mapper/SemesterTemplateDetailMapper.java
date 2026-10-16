package org.unischeduler.academic_programming.infrastructure.mapper;

import org.unischeduler.academic_programming.domain.entity.Group;
import org.unischeduler.academic_programming.domain.entity.SemesterTemplateDetail;
import org.unischeduler.academic_programming.infrastructure.entity.SemesterTemplateDetailEntity;

public class SemesterTemplateDetailMapper {

    public static SemesterTemplateDetailEntity toEntity(
            SemesterTemplateDetail detail,
            Long templateId
    ) {
        SemesterTemplateDetailEntity entity =
                new SemesterTemplateDetailEntity();

        entity.setTemplateDetailId(
                detail.getTemplateDetailId()
        );

        entity.setTemplateId(templateId);

        entity.setGroupId(
                detail.getGroup().getGroupId()
        );

        return entity;
    }

    public static SemesterTemplateDetail toDomain(
            SemesterTemplateDetailEntity entity,
            Group group
    ) {
        return new SemesterTemplateDetail(
                entity.getTemplateDetailId(),
                group
        );
    }
}
