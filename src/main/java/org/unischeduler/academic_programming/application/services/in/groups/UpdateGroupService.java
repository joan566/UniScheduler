package org.unischeduler.academic_programming.application.services.in.groups;

import org.unischeduler.academic_programming.application.services.in.group_schedules.RegisterGroupScheduleCommand;
import org.unischeduler.academic_programming.application.services.in.group_schedules.UpdateGroupScheduleCommand;
import org.unischeduler.academic_programming.application.services.in.group_schedules.dtos.UpdateGroupScheduleResponse;
import org.unischeduler.academic_programming.application.services.in.groups.dtos.UpdateGroupResponse;
import org.unischeduler.academic_programming.application.services.out.dtos.CourseInfo;
import org.unischeduler.academic_programming.application.services.out.dtos.GroupInfo;
import org.unischeduler.academic_programming.application.services.out.dtos.GroupScheduleInfo;
import org.unischeduler.academic_programming.application.services.out.dtos.TeacherInfo;
import org.unischeduler.academic_catalog.domain.entity.Course;
import org.unischeduler.academic_programming.domain.entity.Group;
import org.unischeduler.academic_programming.domain.entity.Teacher;
import org.unischeduler.academic_programming.domain.inputports.UpdateGroupUseCase;
import org.unischeduler.academic_programming.domain.inputports.group_schedule.UpdateGroupScheduleUseCase;
import org.unischeduler.academic_catalog.domain.outputports.CourseRepository;
import org.unischeduler.academic_programming.domain.outputports.GroupRepository;
import org.unischeduler.academic_programming.domain.outputports.TeacherRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UpdateGroupService implements UpdateGroupUseCase {

    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final UpdateGroupScheduleUseCase updateGroupScheduleUseCase;

    public UpdateGroupService(
            GroupRepository groupRepository,
            CourseRepository courseRepository,
            TeacherRepository teacherRepository,
            UpdateGroupScheduleUseCase updateGroupScheduleUseCase
    ) {
        this.groupRepository = groupRepository;
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
        this.updateGroupScheduleUseCase = updateGroupScheduleUseCase;
    }

    @Override
    public UpdateGroupResponse execute(UpdateGroupCommand command) {

        if (groupRepository.findById(command.getGroupId()).isEmpty()) {
            return new UpdateGroupResponse(
                    false,
                    "No existe el grupo con id " + command.getGroupId(),
                    null
            );
        }

        Optional<Course> courseOptional =
                courseRepository.findById(command.getCourseId());

        if (courseOptional.isEmpty()) {
            return new UpdateGroupResponse(
                    false,
                    "No existe la asignatura con id " + command.getCourseId(),
                    null
            );
        }

        Optional<Teacher> teacherOptional =
                teacherRepository.findById(command.getTeacherId());

        if (teacherOptional.isEmpty()) {
            return new UpdateGroupResponse(
                    false,
                    "No existe el profesor con id " + command.getTeacherId(),
                    null
            );
        }

        Group group = new Group(
                command.getGroupId(),
                courseOptional.get(),
                command.getGroupCode(),
                teacherOptional.get(),
                command.getCapacity(),
                new ArrayList<>()
        );

        Group groupUpdated = groupRepository.update(group);

        UpdateGroupScheduleResponse schedulesResponse =
                updateGroupScheduleUseCase.execute(
                        new UpdateGroupScheduleCommand(
                                command.getGroupId(),
                                command.getGroupSchedules()
                                        .stream()
                                        .map(RegisterGroupScheduleCommand::toDomain)
                                        .toList()
                        )
                );

        if (!schedulesResponse.isSuccessfully()) {
            return new UpdateGroupResponse(
                    false,
                    schedulesResponse.getMessage(),
                    null
            );
        }

        return new UpdateGroupResponse(
                true,
                "Se actualizó el grupo con éxito.",
                toGroupInfo(groupUpdated, schedulesResponse.getGroupSchedules())
        );
    }

    private GroupInfo toGroupInfo(
            Group group,
            List<GroupScheduleInfo> schedules
    ) {

        Course course = group.getCourse();
        Teacher teacher = group.getTeacher();

        CourseInfo courseInfo = new CourseInfo(
                course.getCourseId(),
                course.getCode(),
                course.getName()
        );

        TeacherInfo teacherInfo = new TeacherInfo(
                teacher.getTeacherId(),
                teacher.getName()
        );

        return new GroupInfo(
                group.getGroupId(),
                courseInfo,
                group.getGroupCode(),
                teacherInfo,
                group.getCapacity(),
                schedules
        );
    }
}