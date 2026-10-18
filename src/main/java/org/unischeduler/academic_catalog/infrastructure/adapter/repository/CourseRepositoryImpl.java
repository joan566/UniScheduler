package org.unischeduler.academic_catalog.infrastructure.adapter.repository;

import org.springframework.stereotype.Repository;
import org.unischeduler.academic_catalog.domain.entity.Course;
import org.unischeduler.academic_catalog.domain.outputports.CourseRepository;
import org.unischeduler.academic_catalog.infrastructure.entity.CourseEntity;
import org.unischeduler.academic_catalog.infrastructure.mapper.CourseMapper;
import org.unischeduler.academic_catalog.infrastructure.repository.postgres.SpringCourseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepositoryImpl implements CourseRepository {
    private final SpringCourseRepository courseRepository;

    public CourseRepositoryImpl(SpringCourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Optional<Course> findById(Long id) {
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
    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }
}
