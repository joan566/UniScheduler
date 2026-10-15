package org.unischeduler.academic_catalog.application.services.in.prerequisite;

public class DeletePrerequisiteCommand {
    private final String courseId;

    public DeletePrerequisiteCommand(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseId() {
        return courseId;
    }
}