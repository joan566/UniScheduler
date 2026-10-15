package org.unischeduler.academic_catalog.application.services.in.course;


import org.unischeduler.academic_catalog.application.services.in.course.dtos.DeleteCourseResponse;
import org.unischeduler.academic_catalog.domain.inputports.course.DeleteCourseUseCase;
import org.unischeduler.academic_catalog.domain.outputports.CourseRepository;
import org.unischeduler.academic_catalog.domain.outputports.PrerequisiteRepository;

public class DeleteCourseService implements DeleteCourseUseCase {
    private final CourseRepository courseRepository;
    private final PrerequisiteRepository prerequisiteRepository;

    public DeleteCourseService(CourseRepository courseRepository, PrerequisiteRepository prerequisiteRepository) {
        this.courseRepository = courseRepository;
        this.prerequisiteRepository = prerequisiteRepository;
    }

    @Override
    public DeleteCourseResponse execute(DeleteCourseCommand command) {
        if(!courseRepository.deleteById(command.getId())) {
            return new DeleteCourseResponse(
                    false,
                    "No existe una asignatura con id " + command.getId()
            );
        }

        prerequisiteRepository.deleteWhereCourseId(command.getId());

        return new DeleteCourseResponse(
                true,
                "Se elimino la asignatura con exito"
        );
    }
}
