package org.unischeduler.academic_programming.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.unischeduler.academic_catalog.domain.outputports.CourseRepository;
import org.unischeduler.academic_programming.application.services.in.group_schedules.DeleteGroupSchedulesService;
import org.unischeduler.academic_programming.application.services.in.group_schedules.RegisterGroupScheduleService;
import org.unischeduler.academic_programming.application.services.in.group_schedules.UpdateGroupScheduleService;
import org.unischeduler.academic_programming.application.services.in.groups.DeleteGroupService;
import org.unischeduler.academic_programming.application.services.in.groups.RegisterGroupService;
import org.unischeduler.academic_programming.application.services.in.groups.UpdateGroupService;
import org.unischeduler.academic_programming.application.services.out.GetScheduleService;
import org.unischeduler.academic_programming.application.services.out.ListAllGroupsServices;
import org.unischeduler.academic_programming.application.services.out.ListAllTeachersService;
import org.unischeduler.academic_programming.domain.inputports.DeleteGroupUseCase;
import org.unischeduler.academic_programming.domain.inputports.ListAllGroupsUseCase;
import org.unischeduler.academic_programming.domain.inputports.ListAllTeachersUseCase;
import org.unischeduler.academic_programming.domain.inputports.RegisterGroupUseCase;
import org.unischeduler.academic_programming.domain.inputports.UpdateGroupUseCase;
import org.unischeduler.academic_programming.domain.inputports.group_schedule.DeleteGroupSchedulesUseCase;
import org.unischeduler.academic_programming.domain.inputports.group_schedule.RegisterGroupScheduleUseCase;
import org.unischeduler.academic_programming.domain.inputports.group_schedule.UpdateGroupScheduleUseCase;
import org.unischeduler.academic_programming.domain.inputports.schedule.GetScheduleUseCase;
import org.unischeduler.academic_programming.domain.outputports.GroupRepository;
import org.unischeduler.academic_programming.domain.outputports.GroupScheduleRepository;
import org.unischeduler.academic_programming.domain.outputports.TeacherRepository;
import org.unischeduler.enrollment.domain.outputports.EnrollmentRepository;

@Configuration
public class AcademicProgrammingConfiguration {
    @Bean
    DeleteGroupUseCase deleteGroupUseCase(GroupRepository groupRepository) {
        return new DeleteGroupService(groupRepository);
    }

    @Bean
    ListAllGroupsUseCase listAllGroupsUseCase(GroupRepository groupRepository) {
        return new ListAllGroupsServices(groupRepository);
    }

    @Bean
    ListAllTeachersUseCase listAllTeachersUseCase(TeacherRepository teacherRepository) {
        return new ListAllTeachersService(teacherRepository);
    }

    @Bean
    RegisterGroupUseCase registerGroupUseCase(
            GroupRepository groupRepository,
            CourseRepository courseRepository,
            TeacherRepository teacherRepository,
            UpdateGroupUseCase updateGroupUseCase
    ) {
        return new RegisterGroupService(
                groupRepository,
                courseRepository,
                teacherRepository,
                updateGroupUseCase
        );
    }

    @Bean
    UpdateGroupUseCase updateGroupUseCase(
            GroupRepository groupRepository,
            CourseRepository courseRepository,
            TeacherRepository teacherRepository,
            UpdateGroupScheduleUseCase updateGroupScheduleUseCase
    ) {
        return new UpdateGroupService(
                groupRepository,
                courseRepository,
                teacherRepository,
                updateGroupScheduleUseCase
        );
    }

    @Bean
    DeleteGroupSchedulesUseCase deleteGroupSchedulesUseCase(GroupScheduleRepository groupScheduleRepository) {
        return new DeleteGroupSchedulesService(groupScheduleRepository);
    }

    @Bean
    RegisterGroupScheduleUseCase registerGroupScheduleUseCase(GroupScheduleRepository groupScheduleRepository) {
        return new RegisterGroupScheduleService(groupScheduleRepository);
    }

    @Bean
    UpdateGroupScheduleUseCase updateGroupScheduleUseCase(GroupScheduleRepository groupScheduleRepository) {
        return new UpdateGroupScheduleService(groupScheduleRepository);
    }

    @Bean
    GetScheduleUseCase getScheduleUseCase(
            EnrollmentRepository enrollmentRepository,
            GroupScheduleRepository groupScheduleRepository
    ) {
        return new GetScheduleService(enrollmentRepository, groupScheduleRepository);
    }
}
