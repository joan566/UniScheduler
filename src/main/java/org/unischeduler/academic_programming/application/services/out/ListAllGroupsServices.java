package org.unischeduler.academic_programming.application.services.out;

import org.unischeduler.academic_programming.application.services.out.dtos.CourseInfo;
import org.unischeduler.academic_programming.application.services.out.dtos.GroupInfo;
import org.unischeduler.academic_programming.application.services.out.dtos.GroupScheduleInfo;
import org.unischeduler.academic_programming.application.services.out.dtos.ListAllGroupsResponse;
import org.unischeduler.academic_programming.application.services.out.dtos.TeacherInfo;
import org.unischeduler.academic_catalog.domain.entity.Course;
import org.unischeduler.academic_programming.domain.entity.Group;
import org.unischeduler.academic_programming.domain.entity.GroupSchedule;
import org.unischeduler.academic_programming.domain.entity.Teacher;
import org.unischeduler.academic_programming.domain.inputports.ListAllGroupsUseCase;
import org.unischeduler.academic_programming.domain.outputports.GroupRepository;

import java.util.ArrayList;
import java.util.List;

public class ListAllGroupsServices implements ListAllGroupsUseCase {
    private final GroupRepository groupRepository;

    public ListAllGroupsServices(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public ListAllGroupsResponse execute() {
        List<Group> groups = groupRepository.findAll();

        List<GroupInfo> groupInfos = new ArrayList<>();
        for(Group group : groups) {
            groupInfos.add(toGroupInfo(group));
        }

        return new ListAllGroupsResponse(
                true,
                "Se encontraron " + groupInfos.size() + " grupos",
                groupInfos
        );
    }

    private GroupInfo toGroupInfo(Group group) {
        Course course = group.getCourse();
        Teacher teacher = group.getTeacher();

        CourseInfo courseInfo = new CourseInfo(
                course.getId(),
                course.getCode(),
                course.getName()
        );

        TeacherInfo teacherInfo = new TeacherInfo(
                teacher.getTeacherId(),
                teacher.getName()
        );

        List<GroupScheduleInfo> scheduleInfos = new ArrayList<>();
        List<GroupSchedule> schedules = group.getSchedules();
        if(schedules != null) {
            for(GroupSchedule schedule : schedules) {
                scheduleInfos.add(new GroupScheduleInfo(
                        group.getGroupId(),
                        courseInfo.getCourseName(),
                        schedule.getGroupScheduleId(),
                        schedule.getDayOfWeek().name(),
                        schedule.getStartTime(),
                        schedule.getEndTime(),
                        schedule.getClassroom()
                ));
            }
        }

        return new GroupInfo(
                group.getGroupId(),
                courseInfo,
                group.getGroupCode(),
                teacherInfo,
                group.getCapacity(),
                scheduleInfos
        );
    }
}
