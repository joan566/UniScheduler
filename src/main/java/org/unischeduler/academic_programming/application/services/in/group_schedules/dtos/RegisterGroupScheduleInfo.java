package org.unischeduler.academic_programming.application.services.in.group_schedules.dtos;

import java.time.LocalTime;

public class RegisterGroupScheduleInfo {
    private final Long groupScheduleId;
    private final Long groupId;
    private final String day;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final String classroom;

    public RegisterGroupScheduleInfo(Long groupScheduleId, Long groupId, String day, LocalTime startTime, LocalTime endTime, String classroom) {
        this.groupScheduleId = groupScheduleId;
        this.groupId = groupId;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.classroom = classroom;
    }

    public Long getGroupScheduleId() {
        return groupScheduleId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public String getDay() {
        return day;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getClassroom() {
        return classroom;
    }
}
