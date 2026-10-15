package org.unischeduler.academic_catalog.infrastructure.mapper;

import org.unischeduler.academic_catalog.domain.entity.Course;
import org.unischeduler.academic_catalog.domain.entity.Prerequisite;
import org.unischeduler.academic_catalog.infrastructure.entity.PrerequisiteEntity;

public class PrerequisiteMapper {

    private PrerequisiteMapper() {
    }

    public static Prerequisite toDomain(
            PrerequisiteEntity entity,
            Course requiredCourse
    ) {
        if (entity == null) {
            return null;
        }

        return new Prerequisite(
                entity.getPrerequisiteId(),
                entity.getCourseId(),
                requiredCourse
        );
    }

    public static PrerequisiteEntity toEntity(
            Prerequisite prerequisite
    ) {
        if (prerequisite == null) {
            return null;
        }

        PrerequisiteEntity entity = new PrerequisiteEntity();

        entity.setPrerequisiteId(prerequisite.getPrerequisite());
        entity.setCourseId(prerequisite.getCourseId());
        entity.setPrerequisiteCourseId(
                prerequisite.getRequiredCourse().getCourseId()
        );

        return entity;
    }
}