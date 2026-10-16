package org.unischeduler.academic_catalog.application.services.in.prerequisite;

public class FindAllPrerequisitesCommand {

    private final Long courseId;

    public FindAllPrerequisitesCommand(
            Long courseId
    ) {
        this.courseId = courseId;
    }

    public Long getCourseId() {
        return courseId;
    }
}