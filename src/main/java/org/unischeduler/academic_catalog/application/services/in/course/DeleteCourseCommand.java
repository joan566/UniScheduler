package org.unischeduler.academic_catalog.application.services.in.course;

public class DeleteCourseCommand {
    private final Long id;

    public DeleteCourseCommand(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
