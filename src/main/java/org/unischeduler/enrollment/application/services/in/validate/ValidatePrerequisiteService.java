package org.unischeduler.enrollment.application.services.in.validate;

import org.unischeduler.enrollment.application.services.in.validate.dtos.ValidatePrerequisiteResponse;
import org.unischeduler.shared.domain.exceptions.EntityNotFoundException;
import org.unischeduler.academic_catalog.domain.entity.Course;
import org.unischeduler.academic_catalog.domain.entity.Prerequisite;
import org.unischeduler.enrollment.domain.enums.EnrollmentStatus;
import org.unischeduler.enrollment.domain.inputports.ValidatePrerequisiteUseCase;
import org.unischeduler.academic_catalog.domain.outputports.CourseRepository;
import org.unischeduler.academic_catalog.domain.outputports.PrerequisiteRepository;
import org.unischeduler.enrollment.domain.outputports.EnrollmentDetailRepository;
import org.unischeduler.enrollment.domain.outputports.EnrollmentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidatePrerequisiteService
        implements ValidatePrerequisiteUseCase {

    private final PrerequisiteRepository prerequisiteRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentDetailRepository enrollmentDetailRepository;
    private final CourseRepository courseRepository;

    public ValidatePrerequisiteService(
            PrerequisiteRepository prerequisiteRepository,
            EnrollmentRepository enrollmentRepository,
            EnrollmentDetailRepository enrollmentDetailRepository,
            CourseRepository courseRepository
    ) {
        this.prerequisiteRepository = prerequisiteRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.enrollmentDetailRepository = enrollmentDetailRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public ValidatePrerequisiteResponse execute(
            ValidatePrerequisiteCommand command
    ) {

        Set<Long> approvedCourses = enrollmentRepository
                .findAllWhereStudentId(command.getStudentId())
                .stream()
                .flatMap(enrollment ->
                        enrollmentDetailRepository
                                .findByEnrollmentId(
                                        enrollment.getId()
                                )
                                .stream())
                .filter(detail ->
                        detail.getStatus() == EnrollmentStatus.COMPLETED)
                .map(detail ->
                        detail.getGroup()
                                .getCourse()
                                .getId())
                .collect(Collectors.toSet());

        List<String> messages = new ArrayList<>();

        List<Course> requestedCourses = command.getCourseIds()
                .stream()
                .map(id -> courseRepository.findById(id)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "No se encontró una asignatura con id "
                                                + id)))
                .toList();

        // 1. Verificar que la materia no haya sido aprobada anteriormente
        for (Course course : requestedCourses) {

            if (approvedCourses.contains(course.getId())) {

                messages.add(
                        "La asignatura "
                                + course.getName()
                                + " ya fue aprobada anteriormente"
                );
            }
        }

        // 2. Verificar prerrequisitos
        for (Course course : requestedCourses) {

            List<Prerequisite> prerequisites =
                    prerequisiteRepository
                            .findAllPrerequisitesWhereCourseId(
                                    course.getId()
                            );

            for (Prerequisite prerequisite : prerequisites) {

                Long requiredCourseId =
                        prerequisite.getRequiredCourse()
                                .getId();

                if (!approvedCourses.contains(requiredCourseId)) {

                    messages.add(
                            "Para cursar "
                                    + course.getName()
                                    + " debe haber aprobado previamente "
                                    + prerequisite
                                    .getRequiredCourse()
                                    .getName()
                    );
                }
            }
        }

        return new ValidatePrerequisiteResponse(
                messages.isEmpty(),
                messages
        );
    }
}