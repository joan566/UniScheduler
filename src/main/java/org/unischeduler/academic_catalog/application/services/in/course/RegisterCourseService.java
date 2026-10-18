package org.unischeduler.academic_catalog.application.services.in.course;



import org.unischeduler.academic_catalog.application.services.in.course.dtos.RegisterCourseResponse;
import org.unischeduler.academic_catalog.application.services.out.course.dtos.CourseInfo;
import org.unischeduler.academic_catalog.application.services.out.course.dtos.PrerequisiteInfo;
import org.unischeduler.academic_catalog.domain.entity.Course;
import org.unischeduler.academic_catalog.domain.entity.Prerequisite;
import org.unischeduler.academic_catalog.domain.inputports.course.RegisterCourseUseCase;
import org.unischeduler.academic_catalog.domain.outputports.CourseRepository;
import org.unischeduler.academic_catalog.domain.outputports.PrerequisiteRepository;

import java.util.ArrayList;
import java.util.List;

public class RegisterCourseService implements RegisterCourseUseCase {

    private final CourseRepository courseRepository;
    private final PrerequisiteRepository prerequisiteRepository;

    public RegisterCourseService(
            CourseRepository courseRepository,
            PrerequisiteRepository prerequisiteRepository
    ) {
        this.courseRepository = courseRepository;
        this.prerequisiteRepository = prerequisiteRepository;
    }

    @Override
    public RegisterCourseResponse execute(RegisterCourseCommand command) {

        if (courseRepository.existsByCode(command.getCourseCode())) {
            return new RegisterCourseResponse(
                    false,
                    "El código de la asignatura ya está en uso",
                    null
            );
        }

        List<Course> prerequisites = new ArrayList<>();
        List<PrerequisiteInfo> prerequisiteInfos = new ArrayList<>();

        for (Long prerequisiteId : command.getPrerequisiteIds()) {

            Course prerequisite = courseRepository
                    .findById(prerequisiteId)
                    .orElseThrow(() ->
                            new IllegalArgumentException(
                                    "El prerrequisito no existe: " + prerequisiteId
                            )
                    );

            prerequisites.add(prerequisite);

            prerequisiteInfos.add(
                    new PrerequisiteInfo(
                            prerequisite.getId(),
                            prerequisite.getCode(),
                            prerequisite.getName()
                    )
            );
        }

        Course course = new Course(
                null,
                command.getName(),
                command.getCourseCode(),
                command.getCredits(),
                command.getDescription(),
                List.of()
        );

        Course courseSaved = courseRepository.save(course);

        List<Prerequisite> prerequisites1 = prerequisites
                .stream()
                .map(prerequisite -> {
                    return prerequisiteRepository.save(
                            new Prerequisite(
                                    null,
                                    courseSaved.getId(),
                                    prerequisite
                            )
                    );
                })
                .toList();

        return new RegisterCourseResponse(
                true,
                "Se creó la asignatura con éxito",
                new CourseInfo(
                        courseSaved.getId(),
                        courseSaved.getName(),
                        courseSaved.getCode(),
                        courseSaved.getCredits(),
                        courseSaved.getDescription(),
                        prerequisiteInfos
                )
        );
    }
}
