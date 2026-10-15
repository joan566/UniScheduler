package org.unischeduler.academic_catalog.domain.inputports.course;


import org.unischeduler.academic_catalog.application.services.in.course.RegisterCourseCommand;
import org.unischeduler.academic_catalog.application.services.in.course.dtos.RegisterCourseResponse;

public interface RegisterCourseUseCase {
    RegisterCourseResponse execute(RegisterCourseCommand command);
}
