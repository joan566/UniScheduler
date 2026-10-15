package org.unischeduler.academic_catalog.application.services.in.prerequisite;

import java.util.List;

public class RegisterPrerequisiteCommand {
    private final String courseId;
    private final List<String> prerequisiteIds;

    public RegisterPrerequisiteCommand(String courseId, List<String> prerequisiteIds) {
        this.courseId = courseId;
        this.prerequisiteIds = prerequisiteIds;
    }

    public String getCourseId() {
        return courseId;
    }

    public List<String> getPrerequisiteIds() {
        return prerequisiteIds;
    }
}
