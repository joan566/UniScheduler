package org.unischeduler.academic_catalog.application.services.in.academic_period;

public class DeleteAcademicPeriodCommand {
    private final String id;

    public DeleteAcademicPeriodCommand(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
