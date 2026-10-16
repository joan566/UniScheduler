package org.unischeduler.enrollment.infrastructure.mapper;

import org.unischeduler.academic_programming.domain.entity.Group;
import org.unischeduler.enrollment.domain.entity.EnrollmentDetail;
import org.unischeduler.enrollment.domain.enums.EnrollmentStatus;
import org.unischeduler.enrollment.infrastructure.entity.EnrollmentDetailEntity;

public class EnrollmentDetailMapper {

    public static EnrollmentDetailEntity toEntity(
            EnrollmentDetail detail,
            Long enrollmentId
    ) {
        EnrollmentDetailEntity entity = new EnrollmentDetailEntity();

        entity.setEnrollmentDetailId(
                detail.getEnrollmentDetailId()
        );

        entity.setEnrollmentId(enrollmentId);

        entity.setGroupId(
                detail.getGroup().getGroupId()
        );

        entity.setStatus(
                detail.getStatus().name()
        );

        entity.setFinalGrade(
                detail.getFinalGrade()
        );

        return entity;
    }

    public static EnrollmentDetail toDomain(
            EnrollmentDetailEntity entity,
            Group group
    ) {
        return new EnrollmentDetail(
                entity.getEnrollmentDetailId(),
                group,
                EnrollmentStatus.valueOf(
                        entity.getStatus()
                ),
                entity.getFinalGrade()
        );
    }
}