package org.unischeduler.academic_catalog.domain.inputports.course;


import org.unischeduler.academic_catalog.application.services.in.course.DeleteCourseCommand;
import org.unischeduler.academic_catalog.application.services.in.course.dtos.DeleteCourseResponse;

public interface DeleteCourseUseCase {
    DeleteCourseResponse execute(DeleteCourseCommand command);
}
