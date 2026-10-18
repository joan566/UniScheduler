package org.unischeduler.academic_programming.application.services.in.groups;

import org.unischeduler.academic_programming.application.services.in.group_schedules.RegisterGroupScheduleCommand;

import java.util.List;

public class UpdateGroupCommand {
    private final Long groupId;
    private final Long courseId;
    private final String groupCode;
    private final Long teacherId;
    private final int capacity;
    private final Long academicPeriodId;
    private final List<RegisterGroupScheduleCommand> groupSchedules;

    public UpdateGroupCommand(Long groupId, Long courseId, String groupCode, Long teacherId, int capacity, List<RegisterGroupScheduleCommand> groupSchedules) {
        this(groupId, courseId, groupCode, teacherId, capacity, null, groupSchedules);
    }

    public UpdateGroupCommand(Long groupId, Long courseId, String groupCode, Long teacherId, int capacity, Long academicPeriodId, List<RegisterGroupScheduleCommand> groupSchedules) {
        this.groupId = groupId;
        this.courseId = courseId;
        this.groupCode = groupCode;
        this.teacherId = teacherId;
        this.capacity = capacity;
        this.academicPeriodId = academicPeriodId;
        this.groupSchedules = groupSchedules;
    }

    public Long getGroupId() {
        return groupId;
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

    public Long getAcademicPeriodId() {
        return academicPeriodId;
    }

    public List<RegisterGroupScheduleCommand> getGroupSchedules() {
        return groupSchedules;
    }
}
