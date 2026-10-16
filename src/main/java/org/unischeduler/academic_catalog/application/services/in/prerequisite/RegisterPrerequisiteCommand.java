package org.unischeduler.academic_catalog.application.services.in.prerequisite;

import java.util.List;

public class RegisterPrerequisiteCommand {
    private final Long courseId;
    private final List<Long> prerequisiteIds;

    public RegisterPrerequisiteCommand(Long courseId, List<Long> prerequisiteIds) {
        this.courseId = courseId;
        this.prerequisiteIds = prerequisiteIds;
    }

    public Long getCourseId() {
        return courseId;
    }

    public List<Long> getPrerequisiteIds() {
        return prerequisiteIds;
    }
}
