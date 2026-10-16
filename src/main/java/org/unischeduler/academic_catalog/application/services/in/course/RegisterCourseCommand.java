package org.unischeduler.academic_catalog.application.services.in.course;

import java.util.List;

public class RegisterCourseCommand {

    private final String courseCode;
    private final String name;
    private final int credits;
    private final String description;
    private final List<Long> prerequisiteIds;

    public RegisterCourseCommand(
            String courseCode,
            String name,
            int credits,
            String description,
            List<Long> prerequisiteIds
    ) {
        this.courseCode = courseCode;
        this.name = name;
        this.credits = credits;
        this.description = description;
        this.prerequisiteIds = prerequisiteIds;
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