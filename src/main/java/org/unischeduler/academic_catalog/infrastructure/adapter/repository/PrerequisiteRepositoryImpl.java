package org.unischeduler.academic_catalog.infrastructure.adapter.repository;

import org.springframework.stereotype.Repository;
import org.unischeduler.academic_catalog.domain.entity.Course;
import org.unischeduler.academic_catalog.domain.entity.Prerequisite;
import org.unischeduler.academic_catalog.domain.outputports.CourseRepository;
import org.unischeduler.academic_catalog.domain.outputports.PrerequisiteRepository;
import org.unischeduler.academic_catalog.infrastructure.entity.PrerequisiteEntity;
import org.unischeduler.academic_catalog.infrastructure.mapper.PrerequisiteMapper;
import org.unischeduler.academic_catalog.infrastructure.repository.postgres.SpringPrerequisiteRepository;
import org.unischeduler.shared.domain.exceptions.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PrerequisiteRepositoryImpl implements PrerequisiteRepository {
    private final SpringPrerequisiteRepository prerequisiteRepository;
    private final CourseRepository courseRepository;

    public PrerequisiteRepositoryImpl(SpringPrerequisiteRepository prerequisiteRepository, CourseRepository courseRepository) {
        this.prerequisiteRepository = prerequisiteRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Prerequisite> findAllPrerequisitesWhereCourseId(Long courseId) {
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
    public Prerequisite save(Prerequisite prerequisite) {

        PrerequisiteEntity entity = prerequisiteRepository.save(
                PrerequisiteMapper.toEntity(prerequisite)
        );

        Course course = courseRepository.findById(entity.getCourseId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "No se encontro un curso con id: " + entity.getCourseId()
                ));

        return PrerequisiteMapper.toDomain(entity, course);
    }

    @Override
    public boolean deleteWhereCourseId(Long id) {
        return prerequisiteRepository.deleteWhereCourseId(id);
    }
}
