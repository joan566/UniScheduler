package org.unischeduler.academic_catalog.application.services.in.prerequisite;

import org.unischeduler.academic_catalog.domain.inputports.prerequisite.DeletePrerequisiteUseCase;
import org.unischeduler.academic_catalog.domain.outputports.CourseRepository;
import org.unischeduler.academic_catalog.domain.outputports.PrerequisiteRepository;

public class DeletePrerequisiteService implements DeletePrerequisiteUseCase {

    private final PrerequisiteRepository prerequisiteRepository;
    private final CourseRepository courseRepository;

    public DeletePrerequisiteService(
            PrerequisiteRepository prerequisiteRepository,
            CourseRepository courseRepository
    ) {
        this.prerequisiteRepository = prerequisiteRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public void execute(DeletePrerequisiteCommand command) {

        courseRepository.findById(
                command.getCourseId()
        ).orElseThrow(() ->
                new IllegalArgumentException(
                        "El curso no existe."
                )
        );

        boolean deleted =
                prerequisiteRepository.deleteWhereCourseId(
                        command.getCourseId()
                );
    }
}