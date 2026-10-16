package org.unischeduler.academic_catalog.application.services.in.course;



import org.unischeduler.academic_catalog.application.services.in.course.dtos.UpdateCourseResponse;
import org.unischeduler.academic_catalog.application.services.in.prerequisite.RegisterPrerequisiteCommand;
import org.unischeduler.academic_catalog.application.services.out.course.dtos.CourseInfo;
import org.unischeduler.academic_catalog.application.services.out.course.dtos.PrerequisiteInfo;
import org.unischeduler.academic_catalog.domain.entity.Course;
import org.unischeduler.academic_catalog.domain.inputports.course.UpdateCourseUseCase;
import org.unischeduler.academic_catalog.domain.inputports.prerequisite.RegisterPrerequisiteUseCase;
import org.unischeduler.academic_catalog.domain.outputports.CourseRepository;

import java.util.ArrayList;
import java.util.List;

public class UpdateCourseService implements UpdateCourseUseCase {

    private final CourseRepository courseRepository;
    private final RegisterPrerequisiteUseCase registerPrerequisiteUseCase;

    public UpdateCourseService(
            CourseRepository courseRepository,
            RegisterPrerequisiteUseCase registerPrerequisiteUseCase
    ) {
        this.courseRepository = courseRepository;
        this.registerPrerequisiteUseCase = registerPrerequisiteUseCase;
    }

    @Override
    public UpdateCourseResponse execute(UpdateCourseCommand command) {

        Course existingCourse = courseRepository
                .findById(command.getCourseId())
                .orElseThrow(() ->
                        new IllegalArgumentException("La asignatura no existe")
                );

        // Validación de código único
        courseRepository.findByCode(command.getCourseCode())
                .ifPresent(course -> {
                    if (!course.getCourseId().equals(command.getCourseId())) {
                        throw new IllegalArgumentException(
                                "El código de la asignatura ya está en uso"
                        );
                    }
                });

        // Validar que existan todos los prerrequisitos por ID
        List<Long> prerequisiteIds = new ArrayList<>();
        List<PrerequisiteInfo> prerequisiteInfos = new ArrayList<>();

        for (Long prerequisiteId : command.getPrerequisiteIds()) {

            Course prerequisite = courseRepository
                    .findById(prerequisiteId)
                    .orElseThrow(() ->
                            new IllegalArgumentException(
                                    "El prerrequisito no existe: " + prerequisiteId
                            )
                    );

            prerequisiteIds.add(prerequisite.getCourseId());

            prerequisiteInfos.add(
                    new PrerequisiteInfo(
                            prerequisite.getCourseId(),
                            prerequisite.getCode(),
                            prerequisite.getName()
                    )
            );
        }

        // Actualizar curso
        Course course = new Course(
                command.getCourseId(),
                command.getName(),
                command.getCourseCode(),
                command.getCredits(),
                command.getDescription(),
                List.of() // prerrequisitos se manejan aparte
        );

        Course updatedCourse = courseRepository.update(course);

        // Reemplazar prerrequisitos (ya lo hace el use case)
        registerPrerequisiteUseCase.execute(
                new RegisterPrerequisiteCommand(
                        updatedCourse.getCourseId(),
                        prerequisiteIds
                )
        );

        return new UpdateCourseResponse(
                true,
                "Se actualizó la asignatura con éxito",
                new CourseInfo(
                        updatedCourse.getCourseId(),
                        updatedCourse.getName(),
                        updatedCourse.getCode(),
                        updatedCourse.getCredits(),
                        updatedCourse.getDescription(),
                        prerequisiteInfos
                )
        );
    }
}
