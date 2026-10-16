package org.unischeduler.academic_programming.application.services.out.dtos;

public class TeacherInfo {
    private final Long teacherId;
    private final String teacherName;

    public TeacherInfo(Long teacherId, String teacherName) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }
}
