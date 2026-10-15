package org.unischeduler.academic_catalog.domain.inputports.course;


import org.unischeduler.academic_catalog.application.services.out.course.dtos.ListAllCoursesResponse;

public interface ListAllCoursesUseCase {
    ListAllCoursesResponse execute();
}
