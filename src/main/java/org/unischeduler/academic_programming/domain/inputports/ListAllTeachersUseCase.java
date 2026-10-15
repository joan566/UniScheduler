package org.unischeduler.academic_programming.domain.inputports;

import org.unischeduler.academic_programming.application.services.out.dtos.TeacherInfo;

import java.util.List;

public interface ListAllTeachersUseCase {
    List<TeacherInfo> execute();
}
