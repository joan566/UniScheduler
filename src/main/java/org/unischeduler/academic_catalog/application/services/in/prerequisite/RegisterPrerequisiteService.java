package org.unischeduler.academic_catalog.application.services.in.prerequisite;

import org.unischeduler.academic_catalog.application.services.in.prerequisite.dtos.RegisterPrerequisiteResponse;
import org.unischeduler.academic_catalog.domain.entity.Course;
import org.unischeduler.academic_catalog.domain.entity.Prerequisite;
import org.unischeduler.academic_catalog.domain.inputports.prerequisite.DeletePrerequisiteUseCase;
import org.unischeduler.academic_catalog.domain.inputports.prerequisite.RegisterPrerequisiteUseCase;
import org.unischeduler.academic_catalog.domain.outputports.CourseRepository;
import org.unischeduler.academic_catalog.domain.outputports.PrerequisiteRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RegisterPrerequisiteService
        implements RegisterPrerequisiteUseCase {

    private final PrerequisiteRepository prerequisiteRepository;
    private final CourseRepository courseRepository;
    private final DeletePrerequisiteUseCase deletePrerequisiteUseCase;

    public RegisterPrerequisiteService(
            PrerequisiteRepository prerequisiteRepository,
            CourseRepository courseRepository,
            DeletePrerequisiteUseCase deletePrerequisiteUseCase
    ) {
        this.prerequisiteRepository = prerequisiteRepository;
        this.courseRepository = courseRepository;
        this.deletePrerequisiteUseCase = deletePrerequisiteUseCase;
    }

    @Override
    public RegisterPrerequisiteResponse execute(RegisterPrerequisiteCommand command) {

        Course course = courseRepository
                .findById(command.getCourseId())
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "El curso no existe."
                        )
                );

        Set<Long> uniquePrerequisiteIds =
                new HashSet<>(command.getPrerequisiteIds());

        if (uniquePrerequisiteIds.size()
                != command.getPrerequisiteIds().size()) {

            throw new IllegalArgumentException(
                    "La lista contiene prerrequisitos duplicados."
            );
        }

        for (Long prerequisiteId : uniquePrerequisiteIds) {

            if (course.getCourseId().equals(prerequisiteId)) {

                throw new IllegalArgumentException(
                        "Un curso no puede ser prerrequisito de sí mismo."
                );
            }

            courseRepository.findById(prerequisiteId)
                    .orElseThrow(() ->
                            new IllegalArgumentException(
                                    "El prerrequisito no existe: " + prerequisiteId
                            )
                    );
        }

        // ==========================
        // 🔥 FIX IMPORTANTE AQUÍ
        // ==========================

        List<Prerequisite> existing =
                prerequisiteRepository
                        .findAllPrerequisitesWhereCourseId(course.getCourseId());

        Set<Long> existingIds =
                existing.stream()
                        .map(Prerequisite::getPrerequisite)
                        .collect(java.util.stream.Collectors.toSet());

        // solo elimina si realmente hay cambios
        if (!existingIds.equals(uniquePrerequisiteIds)) {

            deletePrerequisiteUseCase.execute(
                    new DeletePrerequisiteCommand(
                            course.getCourseId()
                    )
            );

            for (Long prerequisiteId : uniquePrerequisiteIds) {

                prerequisiteRepository.save(
                        course.getCourseId(),
                        prerequisiteId
                );
            }
        }

        return new RegisterPrerequisiteResponse(
                true,
                "Se actualizaron los prerrequisitos con éxito."
        );
    }
}