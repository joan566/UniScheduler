package org.unischeduler.academic_catalog.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.unischeduler.academic_catalog.application.services.in.academic_period.DeleteAcademicPeriodService;
import org.unischeduler.academic_catalog.application.services.in.academic_period.RegisterAcademicPeriodService;
import org.unischeduler.academic_catalog.application.services.in.academic_period.UpdateAcademicPeriodService;
import org.unischeduler.academic_catalog.application.services.in.course.DeleteCourseService;
import org.unischeduler.academic_catalog.application.services.in.course.RegisterCourseService;
import org.unischeduler.academic_catalog.application.services.in.course.UpdateCourseService;
import org.unischeduler.academic_catalog.application.services.in.prerequisite.DeletePrerequisiteService;
import org.unischeduler.academic_catalog.application.services.in.prerequisite.FindAllPrerequisitesService;
import org.unischeduler.academic_catalog.application.services.in.prerequisite.RegisterPrerequisiteService;
import org.unischeduler.academic_catalog.application.services.out.academic_period.ListAllAcademicPeriodService;
import org.unischeduler.academic_catalog.application.services.out.academic_programs.ListAllProgramsService;
import org.unischeduler.academic_catalog.application.services.out.course.ListAllCoursesServices;
import org.unischeduler.academic_catalog.domain.inputports.academic_period.DeleteAcademicPeriodUseCase;
import org.unischeduler.academic_catalog.domain.inputports.academic_period.ListAllAcademicPeriodsUseCase;
import org.unischeduler.academic_catalog.domain.inputports.academic_period.RegisterAcademicPeriodUseCase;
import org.unischeduler.academic_catalog.domain.inputports.academic_period.UpdateAcademicPeriodUseCase;
import org.unischeduler.academic_catalog.domain.inputports.academic_program.ListAllProgramsUseCase;
import org.unischeduler.academic_catalog.domain.inputports.course.DeleteCourseUseCase;
import org.unischeduler.academic_catalog.domain.inputports.course.ListAllCoursesUseCase;
import org.unischeduler.academic_catalog.domain.inputports.course.RegisterCourseUseCase;
import org.unischeduler.academic_catalog.domain.inputports.course.UpdateCourseUseCase;
import org.unischeduler.academic_catalog.domain.inputports.prerequisite.DeletePrerequisiteUseCase;
import org.unischeduler.academic_catalog.domain.inputports.prerequisite.FindAllPrerequisitesUseCase;
import org.unischeduler.academic_catalog.domain.inputports.prerequisite.RegisterPrerequisiteUseCase;
import org.unischeduler.academic_catalog.domain.outputports.AcademicPeriodRepository;
import org.unischeduler.academic_catalog.domain.outputports.AcademicProgramRepository;
import org.unischeduler.academic_catalog.domain.outputports.CourseRepository;
import org.unischeduler.academic_catalog.domain.outputports.PrerequisiteRepository;

@Configuration
public class AcademicCatalogConfiguration {
    @Bean
    DeleteAcademicPeriodUseCase deleteAcademicPeriodUseCase(AcademicPeriodRepository academicPeriodRepository) {
        return new DeleteAcademicPeriodService(academicPeriodRepository);
    }

    @Bean
    ListAllAcademicPeriodsUseCase listAllAcademicPeriodsUseCase(AcademicPeriodRepository academicPeriodRepository) {
        return new ListAllAcademicPeriodService(academicPeriodRepository);
    }

    @Bean
    RegisterAcademicPeriodUseCase registerAcademicPeriodUseCase(AcademicPeriodRepository academicPeriodRepository) {
        return new RegisterAcademicPeriodService(academicPeriodRepository);
    }

    @Bean
    UpdateAcademicPeriodUseCase updateAcademicPeriodUseCase(AcademicPeriodRepository academicPeriodRepository) {
        return new UpdateAcademicPeriodService(academicPeriodRepository);
    }

    @Bean
    ListAllProgramsUseCase listAllProgramsUseCase(AcademicProgramRepository academicProgramRepository) {
        return new ListAllProgramsService(academicProgramRepository);
    }

    @Bean
    DeleteCourseUseCase deleteCourseUseCase(
            CourseRepository courseRepository,
            PrerequisiteRepository prerequisiteRepository
    ) {
        return new DeleteCourseService(courseRepository, prerequisiteRepository);
    }

    @Bean
    ListAllCoursesUseCase listAllCoursesUseCase(
            CourseRepository courseRepository,
            FindAllPrerequisitesUseCase findAllPrerequisitesUseCase
    ) {
        return new ListAllCoursesServices(courseRepository, findAllPrerequisitesUseCase);
    }

    @Bean
    RegisterCourseUseCase registerCourseUseCase(
            CourseRepository courseRepository,
            PrerequisiteRepository prerequisiteRepository
    ) {
        return new RegisterCourseService(courseRepository, prerequisiteRepository);
    }

    @Bean
    UpdateCourseUseCase updateCourseUseCase(
            CourseRepository courseRepository,
            RegisterPrerequisiteUseCase registerPrerequisiteUseCase
    ) {
        return new UpdateCourseService(courseRepository, registerPrerequisiteUseCase);
    }

    @Bean
    DeletePrerequisiteUseCase deletePrerequisiteUseCase(
            PrerequisiteRepository prerequisiteRepository,
            CourseRepository courseRepository
    ) {
        return new DeletePrerequisiteService(prerequisiteRepository, courseRepository);
    }

    @Bean
    FindAllPrerequisitesUseCase findAllPrerequisitesUseCase(
            PrerequisiteRepository prerequisiteRepository,
            CourseRepository courseRepository
    ) {
        return new FindAllPrerequisitesService(prerequisiteRepository, courseRepository);
    }

    @Bean
    RegisterPrerequisiteUseCase registerPrerequisiteUseCase(
            PrerequisiteRepository prerequisiteRepository,
            CourseRepository courseRepository,
            DeletePrerequisiteUseCase deletePrerequisiteUseCase
    ) {
        return new RegisterPrerequisiteService(
                prerequisiteRepository,
                courseRepository,
                deletePrerequisiteUseCase
        );
    }
}
