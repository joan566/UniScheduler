package org.unischeduler.academic_history.application.services;

import org.unischeduler.enrollment.domain.entity.Enrollment;
import org.unischeduler.enrollment.domain.entity.EnrollmentDetail;
import org.unischeduler.enrollment.domain.enums.EnrollmentStatus;
import org.unischeduler.academic_history.domain.inputports.academic_history.GetAcademicHistoryUseCase;
import org.unischeduler.enrollment.domain.outputports.EnrollmentRepository;

import java.util.List;
import java.util.Optional;

public class GetAcademicHistoryService implements GetAcademicHistoryUseCase {
    private final EnrollmentRepository enrollmentRepository;

    public GetAcademicHistoryService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public GetAcademicHistoryResponse execute(GetAcademicHistoryCommand command) {

        List<Enrollment> enrollments =
                enrollmentRepository.findAllWhereStudentId(command.getStudentId());

        if (enrollments.isEmpty()) {
            return new GetAcademicHistoryResponse(
                    false,
                    "No se encontró historial académico",
                    "",
                    0,
                    0.0,
                    List.of()
            );
        }

        List<AcademicHistoryInfo> history = enrollments.stream()
                .flatMap(enrollment ->
                        enrollment.getDetails().stream()
                                .map(detail ->
                                        AcademicHistoryInfo.fromEnrollmentDetail(
                                                detail,
                                                enrollment.getAcademicPeriod().getCode()
                                        )
                                )
                )
                .toList();

        String programName = enrollments.getFirst()
                .getAcademicProgram()
                .getName();

        int approvedCredits = enrollments.stream()
                .flatMap(enrollment -> enrollment.getDetails().stream())
                .filter(detail -> detail.getStatus() == EnrollmentStatus.COMPLETED)
                .mapToInt(detail -> detail.getGroup().getCourse().getCredits())
                .sum();

        double average = enrollments.stream()
                .flatMap(enrollment -> enrollment.getDetails().stream())
                .mapToDouble(EnrollmentDetail::getFinalGrade)
                .average()
                .orElse(0.0);

        return new GetAcademicHistoryResponse(
                true,
                "Historial académico obtenido correctamente",
                programName,
                approvedCredits,
                average,
                history
        );
    }
}
