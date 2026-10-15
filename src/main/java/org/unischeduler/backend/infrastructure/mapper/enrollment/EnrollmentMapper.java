package org.unischeduler.backend.infrastructure.mapper.enrollment;

import org.unischeduler.backend.domain.model.academic_catalog.entity.AcademicPeriod;
import org.unischeduler.backend.domain.model.academic_catalog.entity.AcademicProgram;
import org.unischeduler.backend.domain.model.enrollment.entity.Enrollment;
import org.unischeduler.backend.domain.model.enrollment.entity.EnrollmentDetail;
import org.unischeduler.backend.domain.model.enrollment.entity.Student;
import org.unischeduler.backend.infrastructure.entity.enrollment.EnrollmentEntity;

import java.util.ArrayList;

public class EnrollmentMapper {

    public static EnrollmentEntity toEntity(Enrollment enrollment) {
        EnrollmentEntity entity = new EnrollmentEntity();

        entity.setEnrollmentId(
                enrollment.getEnrollmentId()
        );

        entity.setStudentId(
                enrollment.getStudent().getStudentId()
        );

        entity.setAcademicProgramId(
                enrollment.getAcademicProgram().getAcademicProgramId()
        );

        entity.setEnrollmentDate(
                enrollment.getEnrollmentDate()
        );


        entity.setAcademicPeriodId(enrollment.getAcademicPeriod().getAcademicPeriodId());

        return entity;
    }

    public static Enrollment toDomain(
            EnrollmentEntity entity,
            Student student,
            AcademicProgram academicProgram,
            ArrayList<EnrollmentDetail> details,
            AcademicPeriod academicPeriod
    ) {
        return new Enrollment(
                entity.getEnrollmentId(),
                student,
                academicProgram,
                entity.getEnrollmentDate(),
                details,
                academicPeriod
        );
    }
}
