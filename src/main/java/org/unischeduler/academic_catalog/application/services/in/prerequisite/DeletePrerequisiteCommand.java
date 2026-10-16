package org.unischeduler.academic_catalog.application.services.in.prerequisite;

public class DeletePrerequisiteCommand {
    private final Long courseId;

    public DeletePrerequisiteCommand(Long courseId) {
        this.courseId = courseId;
    }

    public Long getCourseId() {
        return courseId;
    }
}