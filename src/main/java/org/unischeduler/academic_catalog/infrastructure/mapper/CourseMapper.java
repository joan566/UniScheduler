package org.unischeduler.academic_catalog.infrastructure.mapper;

import org.unischeduler.academic_catalog.domain.entity.Course;
import org.unischeduler.academic_catalog.infrastructure.entity.CourseEntity;

import java.util.ArrayList;
import java.util.List;

public class CourseMapper {

    //================// Entity -> Domain //================//
    public static Course toDomain(CourseEntity entity) {
        if (entity == null) return null;

        return new Course(
                entity.getId(),
                entity.getName(),
                entity.getCode(),
                entity.getCredits(),
                entity.getDescription(),
                new ArrayList<>()
        );
    }

    //================// Domain -> Entity //================//
    public static CourseEntity toEntity(Course course) {
        if (course == null) return null;

        CourseEntity entity = new CourseEntity();
        entity.setId(course.getCourseId());
        entity.setName(course.getName());
        entity.setCode(course.getCode());
        entity.setCredits(course.getCredits());
        entity.setDescription(course.getDescription());

        return entity;
    }

    //================// List conversions //================//
    public static List<Course> toDomainList(List<CourseEntity> entities) {
        if (entities == null) return new ArrayList<>();

        List<Course> courses = new ArrayList<>();
        for (CourseEntity entity : entities) {
            courses.add(toDomain(entity));
        }
        return courses;
    }

    public static List<CourseEntity> toEntityList(List<Course> courses) {
        if (courses == null) return new ArrayList<>();

        List<CourseEntity> entities = new ArrayList<>();
        for (Course course : courses) {
            entities.add(toEntity(course));
        }
        return entities;
    }
}