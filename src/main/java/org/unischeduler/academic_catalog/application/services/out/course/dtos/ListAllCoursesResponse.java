package org.unischeduler.academic_catalog.application.services.out.course.dtos;


import java.util.List;

public class ListAllCoursesResponse {
    private final boolean succssesfully;
    private final String messge;
    private final List<CourseInfo> course;

    public ListAllCoursesResponse(boolean succssesfully, String messge, List<CourseInfo> course) {
        this.succssesfully = succssesfully;
        this.messge = messge;
        this.course = course;
    }

    public boolean isSuccssesfully() {
        return succssesfully;
    }

    public String getMessge() {
        return messge;
    }

    public List<CourseInfo> getCourses() {
        return course;
    }
}
