package org.unischeduler.academic_catalog.infrastructure.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.unischeduler.academic_catalog.infrastructure.entity.PrerequisiteEntity;

import java.util.List;

public interface SpringPrerequisiteRepository extends JpaRepository<PrerequisiteEntity, Long> {

    @Query(value = """
            SELECT *
            FROM course_id
            WHERE course_id = :course_id
            """, nativeQuery = true)
    List<PrerequisiteEntity> findAllPrerequisitesWhereCourseId(@Param("course_id") Long courseId);

    @Query(value = """
            DELETE FROM course_id
            WHERE course_id = :course_id
            """, nativeQuery = true)
    boolean deleteWhereCourseId(@Param("course_id") Long courseId);
}
