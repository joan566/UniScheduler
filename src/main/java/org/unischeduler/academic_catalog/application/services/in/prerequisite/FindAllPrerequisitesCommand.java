package org.unischeduler.academic_catalog.application.services.in.prerequisite;

public class FindAllPrerequisitesCommand {

    private final String courseId;

    public FindAllPrerequisitesCommand(
            String courseId
    ) {
        this.courseId = courseId;
    }

    public String getCourseId() {
        return courseId;
    }
}