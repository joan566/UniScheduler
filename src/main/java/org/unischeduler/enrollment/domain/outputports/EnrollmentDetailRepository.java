package org.unischeduler.enrollment.domain.outputports;

import org.unischeduler.enrollment.domain.entity.EnrollmentDetail;

import java.util.ArrayList;

public interface EnrollmentDetailRepository {
    EnrollmentDetail save(EnrollmentDetail enrollmentDetail, Long enrollmentId);

    ArrayList<EnrollmentDetail> findByEnrollmentId(Long enrollmentId);
}
