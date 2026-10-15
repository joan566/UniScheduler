package org.unischeduler.academic_catalog.application.services.in.prerequisite;

import org.unischeduler.academic_catalog.application.services.in.prerequisite.dtos.FindAllPrerequisitesResponse;
import org.unischeduler.academic_catalog.application.services.out.course.dtos.PrerequisiteInfo;
import org.unischeduler.academic_catalog.domain.entity.Course;
import org.unischeduler.academic_catalog.domain.inputports.prerequisite.FindAllPrerequisitesUseCase;
import org.unischeduler.academic_catalog.domain.outputports.CourseRepository;
import org.unischeduler.academic_catalog.domain.outputports.PrerequisiteRepository;

import java.util.List;

public class FindAllPrerequisitesService
        implements FindAllPrerequisitesUseCase {

    private final PrerequisiteRepository prerequisiteRepository;
    private final CourseRepository courseRepository;

    public FindAllPrerequisitesService(
            PrerequisiteRepository prerequisiteRepository,
            CourseRepository courseRepository
    ) {
        this.prerequisiteRepository = prerequisiteRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public FindAllPrerequisitesResponse execute(
            FindAllPrerequisitesCommand command
    ) {

        Course course = courseRepository
                .findById(command.getCourseId())
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "El curso no existe."
                        )
                );

        List<PrerequisiteInfo> prerequisites =
                prerequisiteRepository
                        .findAllPrerequisitesWhereCourseId(
                                course.getCourseId()
                        )
                        .stream()
                        .map(PrerequisiteInfo::toInfo)
                        .toList();


        return new FindAllPrerequisitesResponse(
                prerequisites
        );
    }
}
