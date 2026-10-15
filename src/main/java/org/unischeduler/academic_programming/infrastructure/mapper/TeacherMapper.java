package org.unischeduler.academic_programming.infrastructure.mapper;

import org.unischeduler.academic_programming.domain.entity.Teacher;
import org.unischeduler.academic_programming.infrastructure.entity.TeacherEntity;

public class TeacherMapper {

    public static TeacherEntity toEntity(Teacher teacher) {
        TeacherEntity entity = new TeacherEntity();

        entity.setTeacherId(
                teacher.getTeacherId()
        );

        entity.setName(
                teacher.getName()
        );

        return entity;
    }

    public static Teacher toDomain(
            TeacherEntity entity
    ) {
        return new Teacher(
                entity.getTeacherId(),
                entity.getName()
        );
    }
}