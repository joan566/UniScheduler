package org.unischeduler.academic_programming.application.services.out.dtos;

import java.util.List;

public class GroupInfo {
    private final Long groupId;
    private final CourseInfo courseInfo;
    private final String groupCode;
    private final TeacherInfo teacherInfo;
    private final int capacity;
    private final List<GroupScheduleInfo> schedules;

    public GroupInfo(Long groupId, CourseInfo courseInfo, String groupCode, TeacherInfo teacherInfo, int capacity, List<GroupScheduleInfo> schedules) {
        this.groupId = groupId;
        this.courseInfo = courseInfo;
        this.groupCode = groupCode;
        this.teacherInfo = teacherInfo;
        this.capacity = capacity;
        this.schedules = schedules;
    }

    public Long getGroupId() {
        return groupId;
    }

    public CourseInfo getCourseInfo() {
        return courseInfo;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public TeacherInfo getTeacherInfo() {
        return teacherInfo;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<GroupScheduleInfo> getSchedules() {
        return schedules;
    }
}
