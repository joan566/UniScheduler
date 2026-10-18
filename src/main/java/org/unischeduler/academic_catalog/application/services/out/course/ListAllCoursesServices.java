package org.unischeduler.academic_catalog.application.services.out.course;
import org.unischeduler.academic_catalog.application.services.in.prerequisite.FindAllPrerequisitesCommand;
import org.unischeduler.academic_catalog.application.services.in.prerequisite.dtos.FindAllPrerequisitesResponse;
import org.unischeduler.academic_catalog.application.services.out.course.dtos.CourseInfo;
import org.unischeduler.academic_catalog.application.services.out.course.dtos.ListAllCoursesResponse;

import org.unischeduler.academic_catalog.domain.entity.Course;
import org.unischeduler.academic_catalog.domain.inputports.course.ListAllCoursesUseCase;
import org.unischeduler.academic_catalog.domain.inputports.prerequisite.FindAllPrerequisitesUseCase;
import org.unischeduler.academic_catalog.domain.outputports.CourseRepository;

import java.util.ArrayList;
import java.util.List;

public class ListAllCoursesServices implements ListAllCoursesUseCase {

    private final CourseRepository courseRepository;
    private final FindAllPrerequisitesUseCase findAllPrerequisitesUseCase;

    public ListAllCoursesServices(
            CourseRepository courseRepository,
            FindAllPrerequisitesUseCase findAllPrerequisitesUseCase) {
        this.courseRepository = courseRepository;
        this.findAllPrerequisitesUseCase =
                findAllPrerequisitesUseCase;
    }

    @Override
    public ListAllCoursesResponse execute() {

        List<Course> courses =
                courseRepository.findAll();

        List<CourseInfo> courseInfos =
                new ArrayList<>();

        for (Course course : courses) {

            FindAllPrerequisitesResponse
                    prerequisitesResponse =
                    findAllPrerequisitesUseCase.execute(
                            new FindAllPrerequisitesCommand(
                                    course.getId()
                            )
                    );

            CourseInfo courseInfo =
                    new CourseInfo(
                            course.getId(),
                            course.getName(),
                            course.getCode(),
                            course.getCredits(),
                            course.getDescription(),
                            prerequisitesResponse
                                    .getPrerequisites()
                    );

            courseInfos.add(courseInfo);
        }

        return new ListAllCoursesResponse(
                true,
                "Se encontraron "
                        + courseInfos.size()
                        + " asignaturas",
                courseInfos
        );
    }
}