package org.unischeduler.academic_catalog.application.services.in.prerequisite.dtos;

import org.unischeduler.academic_catalog.application.services.out.course.dtos.PrerequisiteInfo;

import java.util.List;

public class FindAllPrerequisitesResponse {

    private final List<PrerequisiteInfo> prerequisites;

    public FindAllPrerequisitesResponse(List<PrerequisiteInfo> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public List<PrerequisiteInfo> getPrerequisites() {
        return prerequisites;
    }
}