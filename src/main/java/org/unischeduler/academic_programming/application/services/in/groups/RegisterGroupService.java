package org.unischeduler.academic_programming.application.services.in.groups;

import org.unischeduler.academic_programming.application.services.in.group_schedules.RegisterGroupScheduleCommand;
import org.unischeduler.academic_programming.application.services.in.groups.dtos.RegisterGroupResponse;
import org.unischeduler.academic_programming.application.services.in.groups.dtos.UpdateGroupResponse;
import org.unischeduler.academic_programming.application.services.out.dtos.CourseInfo;
import org.unischeduler.academic_programming.application.services.out.dtos.GroupInfo;
import org.unischeduler.academic_programming.application.services.out.dtos.GroupScheduleInfo;
import org.unischeduler.academic_programming.application.services.out.dtos.TeacherInfo;
import org.unischeduler.academic_catalog.domain.entity.Course;
import org.unischeduler.academic_programming.domain.entity.Group;
import org.unischeduler.academic_programming.domain.entity.GroupSchedule;
import org.unischeduler.academic_programming.domain.entity.Teacher;
import org.unischeduler.academic_programming.domain.enums.WeekDays;
import org.unischeduler.academic_programming.domain.inputports.RegisterGroupUseCase;
import org.unischeduler.academic_programming.domain.inputports.UpdateGroupUseCase;
import org.unischeduler.academic_catalog.domain.outputports.CourseRepository;
import org.unischeduler.academic_programming.domain.outputports.GroupRepository;
import org.unischeduler.academic_programming.domain.outputports.TeacherRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RegisterGroupService implements RegisterGroupUseCase {

    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final UpdateGroupUseCase updateGroupUseCase;

    public RegisterGroupService(GroupRepository groupRepository, CourseRepository courseRepository, TeacherRepository teacherRepository, UpdateGroupUseCase updateGroupUseCase) {
        this.groupRepository = groupRepository;
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
        this.updateGroupUseCase = updateGroupUseCase;
    }

    @Override
    public RegisterGroupResponse execute(RegisterGroupCommand command) {

        Optional<Course> courseOptional =
                courseRepository.findById(command.getCourseId());

        if (courseOptional.isEmpty()) {
            return new RegisterGroupResponse(
                    false,
                    "No existe la asignatura con id " + command.getCourseId(),
                    null
            );
        }

        Optional<Teacher> teacherOptional =
                teacherRepository.findById(command.getTeacherId());

        if (teacherOptional.isEmpty()) {
            return new RegisterGroupResponse(
                    false,
                    "No existe el profesor con id " + command.getTeacherId(),
                    null
            );
        }

        // Se crea el grupo sin horarios
        Group group = new Group(
                null,
                courseOptional.get(),
                command.getGroupCode(),
                teacherOptional.get(),
                command.getCapacity(),
                new ArrayList<>()
        );

        // Se guarda para obtener el groupId
        Group groupSaved = groupRepository.save(group);

        // Se construyen los horarios usando el groupId generado
        BuildSchedulesResult schedulesResult =
                buildSchedules(
                        groupSaved.getGroupId(),
                        command.getGroupSchedules()
                );

        if (!schedulesResult.success()) {
            return new RegisterGroupResponse(
                    false,
                    schedulesResult.message(),
                    null
            );
        }

        // Se asignan los horarios al grupo y se actualiza
        groupSaved.setSchedules(schedulesResult.schedules());

        UpdateGroupResponse updateGroupResponse = updateGroupUseCase.execute(
                new UpdateGroupCommand(
                        groupSaved.getGroupId(),
                        groupSaved.getCourse().getCourseId(),
                        groupSaved.getGroupCode(),
                        groupSaved.getTeacher().getTeacherId(),
                        groupSaved.getCapacity(),
                        groupSaved.getSchedules()
                                .stream()
                                .map(RegisterGroupScheduleCommand::toCommand).toList()
                )
        );

        return new RegisterGroupResponse(
                true,
                "Se creó el grupo con éxito.",
                updateGroupResponse.getGroup()
        );
    }

    private GroupInfo toGroupInfo(Group group) {

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

        List<GroupScheduleInfo> scheduleInfos = new ArrayList<>();

        if (group.getSchedules() != null) {
            for (GroupSchedule schedule : group.getSchedules()) {

                scheduleInfos.add(
                        new GroupScheduleInfo(
                                group.getGroupId(),
                                courseInfo.getCourseName(),
                                schedule.getGroupScheduleId(),
                                schedule.getDayOfWeek().name(),
                                schedule.getStartTime(),
                                schedule.getEndTime(),
                                schedule.getClassroom()
                        )
                );
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

    private BuildSchedulesResult buildSchedules(
            Long groupId,
            List<RegisterGroupScheduleCommand> commands
    ) {

        List<GroupSchedule> schedules = new ArrayList<>();

        if (commands == null || commands.isEmpty()) {
            return new BuildSchedulesResult(
                    true,
                    "",
                    schedules
            );
        }

        for (RegisterGroupScheduleCommand command : commands) {

            if (!command.getStartTime().isBefore(command.getEndTime())) {
                return new BuildSchedulesResult(
                        false,
                        "La hora de inicio debe ser menor que la hora de finalización.",
                        null
                );
            }

            WeekDays day;

            try {
                day = WeekDays.valueOf(command.getDayOfWeak().toUpperCase());
            } catch (IllegalArgumentException e) {
                return new BuildSchedulesResult(
                        false,
                        "El día '" + command.getDayOfWeak() + "' no es válido.",
                        null
                );
            }

            for (GroupSchedule existing : schedules) {

                if (existing.getDayOfWeek() != day) {
                    continue;
                }

                boolean overlaps =
                        command.getStartTime().isBefore(existing.getEndTime())
                                && command.getEndTime().isAfter(existing.getStartTime());

                if (overlaps) {
                    return new BuildSchedulesResult(
                            false,
                            "Existen horarios superpuestos para el día " + day + ".",
                            null
                    );
                }
            }

            schedules.add(
                    new GroupSchedule(
                            null,
                            groupId,
                            day,
                            command.getStartTime(),
                            command.getEndTime(),
                            command.getClassroom()
                    )
            );
        }

        return new BuildSchedulesResult(
                true,
                "",
                schedules
        );
    }

    private record BuildSchedulesResult(
            boolean success,
            String message,
            List<GroupSchedule> schedules
    ) {
    }
}