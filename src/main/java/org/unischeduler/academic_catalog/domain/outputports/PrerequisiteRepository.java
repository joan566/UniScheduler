package org.unischeduler.academic_catalog.domain.outputports;

import org.unischeduler.academic_catalog.domain.entity.Prerequisite;

import java.util.List;

public interface PrerequisiteRepository {
    List<Prerequisite> findAllPrerequisitesWhereCourseId(Long courseId);
    Prerequisite save(Prerequisite prerequisite);
    boolean deleteWhereCourseId(Long id);
}
