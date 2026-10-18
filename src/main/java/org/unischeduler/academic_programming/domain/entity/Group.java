package org.unischeduler.academic_programming.domain.entity;

import org.unischeduler.academic_catalog.domain.entity.Course;

import java.util.List;

public class Group {

    //================// Atributos //================//
    private Long id;
    private Course course;
    private String groupCode;
    private Teacher teacher;
    private int capacity;
    private Long academicPeriodId;
    private List<GroupSchedule> schedules;


    //================// Constructores //================//

    public Group() {
    }

    public Group(Long id, Course course, String groupCode, Teacher teacher, int capacity, List<GroupSchedule> schedules) {
        this(id, course, groupCode, teacher, capacity, null, schedules);
    }

    public Group(Long id, Course course, String groupCode, Teacher teacher, int capacity, Long academicPeriodId, List<GroupSchedule> schedules) {
        this.id = id;
        this.course = course;
        this.groupCode = groupCode;
        this.teacher = teacher;
        this.capacity = capacity;
        this.academicPeriodId = academicPeriodId;
        this.schedules = schedules;
    }

    //================// Setters y Getters //================//

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public int getCapacity() {
        return capacity;
    }

    public Group setCapacity(int capacity) {
        this.capacity = capacity;

        return this;
    }

    public Long getAcademicPeriodId() {
        return academicPeriodId;
    }

    public void setAcademicPeriodId(Long academicPeriodId) {
        this.academicPeriodId = academicPeriodId;
    }

    public List<GroupSchedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<GroupSchedule> schedules) {
        this.schedules = schedules;
    }


    //================// Funciones Adicionales //================//
}
