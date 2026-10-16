package org.unischeduler.academic_programming.infrastructure.entity;

import java.time.LocalTime;

public class GroupScheduleEntity {
    private Long groupScheduleId;
    private Long groupId;
    private String dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    private String classroom;

    public Long getGroupScheduleId() {
        return groupScheduleId;
    }

    public void setGroupScheduleId(Long groupScheduleId) {
        this.groupScheduleId = groupScheduleId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }
}
