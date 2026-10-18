package org.unischeduler.academic_programming.infrastructure.mapper;

import org.unischeduler.academic_programming.domain.entity.GroupSchedule;
import org.unischeduler.academic_programming.domain.enums.WeekDays;
import org.unischeduler.academic_programming.infrastructure.entity.GroupScheduleEntity;

public class GroupScheduleMapper {

    public static GroupScheduleEntity toEntity(GroupSchedule schedule) {
        GroupScheduleEntity entity = new GroupScheduleEntity();

        entity.setId(
                schedule.getId()
        );

        entity.setGroupId(
                schedule.getGroupId()
        );

        entity.setDayOfWeek(
                schedule.getDayOfWeek().name()
        );

        entity.setStartTime(
                schedule.getStartTime()
        );

        entity.setEndTime(
                schedule.getEndTime()
        );

        entity.setClassroom(
                schedule.getClassroom()
        );

        return entity;
    }

    public static GroupSchedule toDomain(
            GroupScheduleEntity entity
    ) {
        return new GroupSchedule(
                entity.getId(),
                entity.getGroupId(),
                WeekDays.valueOf(entity.getDayOfWeek()),
                entity.getStartTime(),
                entity.getEndTime(),
                entity.getClassroom()
        );
    }
}
