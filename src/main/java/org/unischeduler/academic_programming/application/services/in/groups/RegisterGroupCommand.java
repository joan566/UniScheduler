package org.unischeduler.academic_programming.application.services.in.groups;

import org.unischeduler.academic_programming.application.services.in.group_schedules.RegisterGroupScheduleCommand;

import java.util.List;

public class RegisterGroupCommand {
    private final Long courseId;
    private final String groupCode;
    private final Long teacherId;
    private final int capacity;
    private final List<RegisterGroupScheduleCommand> groupSchedules;

    public RegisterGroupCommand(Long courseId, String groupCode, Long teacherId, int capacity, List<RegisterGroupScheduleCommand> groupSchedules) {
        this.courseId = courseId;
        this.groupCode = groupCode;
        this.teacherId = teacherId;
        this.capacity = capacity;
        this.groupSchedules = groupSchedules;
    }

    public Long getCourseId() {
        return courseId;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<RegisterGroupScheduleCommand> getGroupSchedules() {
        return groupSchedules;
    }
}
