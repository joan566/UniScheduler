package org.unischeduler.academic_catalog.domain.outputports;

import org.unischeduler.academic_catalog.domain.entity.Prerequisite;

import java.util.List;

public interface PrerequisiteRepository {
    List<Prerequisite> findAllPrerequisitesWhereCourseId(String courseId);
    Prerequisite save(String courseId, String coursePrerequisiteId);
    boolean deleteWhereCourseId(String id);
}
