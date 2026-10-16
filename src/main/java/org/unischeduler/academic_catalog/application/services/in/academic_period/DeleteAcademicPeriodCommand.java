package org.unischeduler.academic_catalog.application.services.in.academic_period;

public class DeleteAcademicPeriodCommand {
    private final Long id;

    public DeleteAcademicPeriodCommand(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
