package org.unischeduler.enrollment.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.unischeduler.academic_catalog.domain.outputports.AcademicPeriodRepository;
import org.unischeduler.academic_catalog.domain.outputports.AcademicProgramRepository;
import org.unischeduler.academic_catalog.domain.outputports.CourseRepository;
import org.unischeduler.academic_catalog.domain.outputports.PrerequisiteRepository;
import org.unischeduler.academic_programming.domain.outputports.GroupRepository;
import org.unischeduler.auth.domain.inputports.RegisterUserUseCase;
import org.unischeduler.auth.domain.outputports.PasswordGeneratorPort;
import org.unischeduler.auth.domain.outputports.UserRepository;
import org.unischeduler.enrollment.application.services.in.register.RegisterEnrollmentService;
import org.unischeduler.enrollment.application.services.in.register.RegisterStudentService;
import org.unischeduler.enrollment.application.services.in.validate.ValidateCreditLimitService;
import org.unischeduler.enrollment.application.services.in.validate.ValidatePrerequisiteService;
import org.unischeduler.enrollment.application.services.in.validate.ValidateScheduleConflictsService;
import org.unischeduler.enrollment.domain.inputports.RegisterEnrollmentUseCase;
import org.unischeduler.enrollment.domain.inputports.RegisterStudentUseCase;
import org.unischeduler.enrollment.domain.inputports.ValidateCreditLimitUseCase;
import org.unischeduler.enrollment.domain.inputports.ValidatePrerequisiteUseCase;
import org.unischeduler.enrollment.domain.inputports.ValidateScheduleConflictsUseCase;
import org.unischeduler.enrollment.domain.outputports.EnrollmentDetailRepository;
import org.unischeduler.enrollment.domain.outputports.EnrollmentRepository;
import org.unischeduler.enrollment.domain.outputports.StudentCodeGeneratorPort;
import org.unischeduler.enrollment.domain.outputports.StudentRepository;

@Configuration
public class EnrollmentConfiguration {
    @Bean
    RegisterEnrollmentUseCase registerEnrollmentUseCase(
            EnrollmentRepository enrollmentRepository,
            EnrollmentDetailRepository enrollmentDetailRepository,
            StudentRepository studentRepository,
            GroupRepository groupRepository,
            AcademicProgramRepository academicProgramRepository,
            AcademicPeriodRepository academicPeriodRepository,
            ValidateCreditLimitUseCase validateCreditLimitUseCase,
            ValidatePrerequisiteUseCase validatePrerequisiteUseCase
    ) {
        return new RegisterEnrollmentService(
                enrollmentRepository,
                enrollmentDetailRepository,
                studentRepository,
                groupRepository,
                academicProgramRepository,
                academicPeriodRepository,
                validateCreditLimitUseCase,
                validatePrerequisiteUseCase
        );
    }

    @Bean
    RegisterStudentUseCase registerStudentUseCase(
            RegisterUserUseCase registerUserUseCase,
            UserRepository userRepository,
            StudentRepository studentRepository,
            AcademicProgramRepository academicProgramRepository,
            PasswordGeneratorPort passwordGenerator,
            StudentCodeGeneratorPort studentCodeGenerator
    ) {
        return new RegisterStudentService(
                registerUserUseCase,
                userRepository,
                studentRepository,
                academicProgramRepository,
                passwordGenerator,
                studentCodeGenerator
        );
    }

    @Bean
    ValidateCreditLimitUseCase validateCreditLimitUseCase(
            EnrollmentRepository enrollmentRepository,
            CourseRepository courseRepository
    ) {
        return new ValidateCreditLimitService(enrollmentRepository, courseRepository);
    }

    @Bean
    ValidatePrerequisiteUseCase validatePrerequisiteUseCase(
            PrerequisiteRepository prerequisiteRepository,
            EnrollmentRepository enrollmentRepository,
            EnrollmentDetailRepository enrollmentDetailRepository,
            CourseRepository courseRepository
    ) {
        return new ValidatePrerequisiteService(
                prerequisiteRepository,
                enrollmentRepository,
                enrollmentDetailRepository,
                courseRepository
        );
    }

    @Bean
    ValidateScheduleConflictsUseCase validateScheduleConflictsUseCase(
            EnrollmentRepository enrollmentRepository
    ) {
        return new ValidateScheduleConflictsService(enrollmentRepository);
    }
}
