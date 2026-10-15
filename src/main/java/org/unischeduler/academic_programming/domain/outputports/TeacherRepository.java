package org.unischeduler.academic_programming.domain.outputports;

import org.unischeduler.academic_programming.domain.entity.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository {
    Optional<Teacher> findById(String id);
    List<Teacher> findAll();
}
