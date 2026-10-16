package org.unischeduler.academic_catalog.application.services.in.course;


import java.util.List;

public class UpdateCourseCommand {

    private final Long courseId;
    private final String courseCode;
    private final String name;
    private final int credits;
    private final String description;
    private final List<Long> prerequisiteIds;

    public UpdateCourseCommand(
            Long courseId,
            String courseCode,
            String name,
            int credits,
            String description,
            List<Long> prerequisiteIds
    ) {
        this.courseId = courseId;
        this.courseCode = courseCode;
        this.name = name;
        this.credits = credits;
        this.description = description;
        this.prerequisiteIds = prerequisiteIds;
    }

    public Long getCourseId() {
        return courseId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }

    public String getDescription() {
        return description;
    }

    public List<Long> getPrerequisiteIds() {
        return prerequisiteIds;
    }
}
