package org.unischeduler.enrollment.domain.outputports;

import org.unischeduler.enrollment.domain.entity.Student;

import java.util.Optional;

public interface StudentRepository {
    Student save(Student student);
    Optional<Student> findById(Long id);
    boolean existsByStudentCode(String code);
    Optional<Student> findByUserId(Long userId);
}
