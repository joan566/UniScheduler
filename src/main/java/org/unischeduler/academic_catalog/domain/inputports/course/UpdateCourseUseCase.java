package org.unischeduler.academic_catalog.domain.inputports.course;


import org.unischeduler.academic_catalog.application.services.in.course.UpdateCourseCommand;
import org.unischeduler.academic_catalog.application.services.in.course.dtos.UpdateCourseResponse;

public interface UpdateCourseUseCase {
    UpdateCourseResponse execute(UpdateCourseCommand command);
}
