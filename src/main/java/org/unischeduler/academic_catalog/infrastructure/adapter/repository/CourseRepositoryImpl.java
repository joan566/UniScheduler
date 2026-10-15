package org.unischeduler.academic_catalog.infrastructure.adapter.repository;

import org.unischeduler.academic_catalog.domain.entity.Course;
import org.unischeduler.academic_catalog.domain.outputports.CourseRepository;
import org.unischeduler.academic_catalog.infrastructure.entity.CourseEntity;
import org.unischeduler.academic_catalog.infrastructure.mapper.CourseMapper;
import org.unischeduler.academic_catalog.infrastructure.repository.excel.ExcelCourseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseRepositoryImpl implements CourseRepository {
    private final ExcelCourseRepository courseRepository;

    public CourseRepositoryImpl(ExcelCourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Optional<Course> findById(String id) {
        Optional<CourseEntity> entityOptional = courseRepository.findById(id);
        if(entityOptional.isEmpty()) {
            return Optional.empty();
        }

        CourseEntity entity = entityOptional.get();
        return Optional.of(CourseMapper.toDomain(entity));
    }

    @Override
    public List<Course> findAll() {
        List<CourseEntity> entities = courseRepository.findAll();

        List<Course> courses = new ArrayList<>();
        for(CourseEntity entity : entities) {
            Course course = CourseMapper.toDomain(entity);
            courses.add(course);
        }

        return courses;
    }

    @Override
    public boolean existsByCode(String code) {
        return courseRepository.existsByCode(code);
    }

    @Override
    public Optional<Course> findByCode(String code) {
        Optional<CourseEntity> entityOptional = courseRepository.findByCode(code);
        if(entityOptional.isEmpty()) {
            return Optional.empty();
        }

        CourseEntity entity = entityOptional.get();

        return Optional.of(CourseMapper.toDomain(entity));
    }

    @Override
    public Course save(Course course) {
        CourseEntity entity = CourseMapper.toEntity(course);

        CourseEntity entitySaved = courseRepository.save(entity);
        return CourseMapper.toDomain(entitySaved);
    }

    @Override
    public Course update(Course course) {
        CourseEntity entity = CourseMapper.toEntity(course);

        CourseEntity entitySaved = courseRepository.update(entity);
        return CourseMapper.toDomain(entitySaved);
    }

    @Override
    public boolean deleteById(String id) {
        return courseRepository.deleteById(id);
    }
}
