package org.unischeduler.academic_history.infrastructure.mapper;

import org.unischeduler.academic_catalog.domain.entity.Course;
import org.unischeduler.academic_history.domain.entity.AcademicHistory;
import org.unischeduler.academic_history.domain.enums.AcademicHistoryCourseStatus;
import org.unischeduler.enrollment.domain.entity.Student;
import org.unischeduler.academic_history.infrastructure.entity.AcademicHistoryEntity;

import java.util.ArrayList;
import java.util.List;

public class AcademicHistoryMapper {

    private AcademicHistoryMapper() {
    }

    public static AcademicHistoryEntity toEntity(
            AcademicHistory history
    ) {

        List<String> completedCoursesIds =
                history.getCompletedCourses()
                        .stream()
                        .map(Course::getCourseId)
                        .toList();

        return new AcademicHistoryEntity(
                String.valueOf(history.getHistoryId()),
                history.getStudent().getStudentId(),
                completedCoursesIds,
                history.getGrade(),
                history.getStatus().name()
        );
    }

    public static AcademicHistory toDomain(
            AcademicHistoryEntity entity,
            Student student,
            List<Course> completedCourses
    ) {

        return new AcademicHistory(
                entity.getHistoryId(),
                student,
                new ArrayList<>(completedCourses),
                entity.getGrade(),
                AcademicHistoryCourseStatus.valueOf(
                        entity.getStatus()
                )
        );
    }
}