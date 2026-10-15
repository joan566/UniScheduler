package org.unischeduler.enrollment.application.services.in;

import org.unischeduler.enrollment.application.services.in.validate.ValidateCreditLimitCommand;
import org.unischeduler.enrollment.application.services.in.validate.dtos.ValidateCreditLimitResponse;
import org.unischeduler.backend.domain.exceptions.shared.EntityNotFoundException;
import org.unischeduler.academic_catalog.domain.entity.Course;
import org.unischeduler.academic_programming.domain.entity.Group;
import org.unischeduler.enrollment.domain.entity.Enrollment;
import org.unischeduler.enrollment.domain.entity.EnrollmentDetail;
import org.unischeduler.enrollment.domain.inputports.ValidateCreditLimitUseCase;
import org.unischeduler.academic_catalog.domain.outputports.CourseRepository;
import org.unischeduler.enrollment.domain.outputports.EnrollmentRepository;

import java.util.ArrayList;
import java.util.List;

public class ValidateCreditLimitService
        implements ValidateCreditLimitUseCase {

    private static final int MAX_CREDITS = 19;

    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;

    public ValidateCreditLimitService(
            EnrollmentRepository enrollmentRepository,
            CourseRepository courseRepository) {

        this.enrollmentRepository = enrollmentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public ValidateCreditLimitResponse execute(
            ValidateCreditLimitCommand command) {

        int enrolledCredits = enrollmentRepository
                .findByStudentAndActivePeriod(command.getStudentId())
                .map(Enrollment::getDetails)
                .stream()
                .flatMap(List::stream)
                .map(EnrollmentDetail::getGroup)
                .map(Group::getCourse)
                .mapToInt(Course::getCredits)
                .sum();

        int selectedCredits = command.getCourseIds()
                .stream()
                .map(courseId ->
                        courseRepository.findById(courseId)
                                .orElseThrow(() ->
                                        new EntityNotFoundException(
                                                "Course not found: " + courseId
                                        )
                                )
                )
                .mapToInt(Course::getCredits)
                .sum();

        int usedCredits = enrolledCredits + selectedCredits;

        int availableCredits =
                Math.max(0, MAX_CREDITS - usedCredits);

        boolean successfully =
                usedCredits <= MAX_CREDITS;

        List<String> messages = getStrings(successfully, usedCredits, availableCredits);

        return new ValidateCreditLimitResponse(
                successfully,
                messages,
                usedCredits,
                availableCredits
        );
    }

    private static List<String> getStrings(boolean successfully, int usedCredits, int availableCredits) {
        List<String> messages = new ArrayList<>();

        if (!successfully) {
            messages.add(
                    "Se excede el límite máximo de "
                            + MAX_CREDITS
                            + " créditos."
            );
        }

        messages.add(
                "Créditos utilizados: "
                        + usedCredits
                        + "/"
                        + MAX_CREDITS
        );

        messages.add(
                "Créditos disponibles: "
                        + availableCredits
        );
        return messages;
    }
}