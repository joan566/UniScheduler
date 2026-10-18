package org.unischeduler.academic_programming.domain.entity;


import org.unischeduler.academic_programming.domain.enums.WeekDays;

import java.time.LocalTime;

public class GroupSchedule {
    //================// Atributos //================//
    private Long id;
    private Long groupId;
    private WeekDays dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    private String classroom;

    //================// Constructores //================//


    public GroupSchedule() {
    }

    public GroupSchedule(Long id, Long groupId, WeekDays dayOfWeek, LocalTime startTime, LocalTime endTime, String classroom) {
        this.id = id;
        this.groupId = groupId;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.classroom = classroom;
    }


    //================// Setters y Getters //================//

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public WeekDays getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(WeekDays dayOfWeek) {
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


    //================// Funciones Adicionales //================//
}
