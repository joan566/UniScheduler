package org.unischeduler.academic_catalog.domain.outputports;

import org.unischeduler.academic_catalog.domain.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {
    Optional<Course> findById(Long id);
    List<Course> findAll();
    boolean existsByCode(String code);
    Optional<Course> findByCode(String code);
    Course save(Course course);
    Course update(Course course);
    boolean deleteById(Long id);
}
