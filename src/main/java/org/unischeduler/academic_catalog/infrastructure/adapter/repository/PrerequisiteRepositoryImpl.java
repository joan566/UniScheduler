package org.unischeduler.academic_catalog.infrastructure.adapter.repository;

import org.unischeduler.academic_catalog.domain.entity.Course;
import org.unischeduler.academic_catalog.domain.entity.Prerequisite;
import org.unischeduler.academic_catalog.domain.outputports.CourseRepository;
import org.unischeduler.academic_catalog.domain.outputports.PrerequisiteRepository;
import org.unischeduler.academic_catalog.infrastructure.entity.PrerequisiteEntity;
import org.unischeduler.academic_catalog.infrastructure.mapper.PrerequisiteMapper;
import org.unischeduler.academic_catalog.infrastructure.repository.excel.ExcelPrerequisiteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PrerequisiteRepositoryImpl implements PrerequisiteRepository {
    private final ExcelPrerequisiteRepository prerequisiteRepository;
    private final CourseRepository courseRepository;

    public PrerequisiteRepositoryImpl(ExcelPrerequisiteRepository prerequisiteRepository, CourseRepository courseRepository) {
        this.prerequisiteRepository = prerequisiteRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Prerequisite> findAllPrerequisitesWhereCourseId(String courseId) {
        List<PrerequisiteEntity> entities = prerequisiteRepository.findAllPrerequisitesWhereCourseId(courseId);

        List<Prerequisite> prerequisites = new ArrayList<>();
        for(PrerequisiteEntity entity : entities) {
            Optional<Course> courseRequiredOptional = courseRepository.findById(entity.getPrerequisiteCourseId());
            if(courseRequiredOptional.isEmpty()) {
                continue;
            }

            Course courseRequired = courseRequiredOptional.get();
            Prerequisite prerequisite = PrerequisiteMapper.toDomain(entity, courseRequired);
            prerequisites.add(prerequisite);
        }

        return prerequisites;
    }

    @Override
    public Prerequisite save(String courseId, String coursePrerequisiteId) {
        PrerequisiteEntity entity = prerequisiteRepository.save(courseId, coursePrerequisiteId);
        Optional<Course> course = courseRepository.findById(entity.getCourseId());

        return PrerequisiteMapper.toDomain(entity, course.get());
    }

    @Override
    public boolean deleteWhereCourseId(String id) {
        return prerequisiteRepository.deleteWhereCourseId(id);
    }
}
