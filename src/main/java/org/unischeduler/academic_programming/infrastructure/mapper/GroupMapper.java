package org.unischeduler.academic_programming.infrastructure.mapper;

import org.unischeduler.academic_catalog.domain.entity.Course;
import org.unischeduler.academic_programming.domain.entity.Group;
import org.unischeduler.academic_programming.domain.entity.GroupSchedule;
import org.unischeduler.academic_programming.domain.entity.Teacher;
import org.unischeduler.academic_programming.infrastructure.entity.GroupEntity;

import java.util.List;

public class GroupMapper {

    public static GroupEntity toEntity(Group group) {
        GroupEntity entity = new GroupEntity();

        entity.setGroupId(
                group.getGroupId()
        );

        entity.setCourseId(
                group.getCourse().getCourseId()
        );

        entity.setGroupCode(
                group.getGroupCode()
        );

        entity.setTeacherId(
                group.getTeacher().getTeacherId()
        );

        entity.setCapacity(
                group.getCapacity()
        );

        return entity;
    }

    public static Group toDomain(
            GroupEntity entity,
            Course course,
            Teacher teacher,
            List<GroupSchedule> schedules
    ) {
        return new Group(
                entity.getGroupId(),
                course,
                entity.getGroupCode(),
                teacher,
                entity.getCapacity(),
                schedules
        );
    }
}