package org.unischeduler.academic_history.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.unischeduler.academic_history.application.services.GetAcademicHistoryService;
import org.unischeduler.academic_history.domain.inputports.academic_history.GetAcademicHistoryUseCase;
import org.unischeduler.enrollment.domain.outputports.EnrollmentRepository;

@Configuration
public class AcademicHistoryConfiguration {
    @Bean
    GetAcademicHistoryUseCase getAcademicHistoryUseCase(EnrollmentRepository enrollmentRepository) {
        return new GetAcademicHistoryService(enrollmentRepository);
    }
}
