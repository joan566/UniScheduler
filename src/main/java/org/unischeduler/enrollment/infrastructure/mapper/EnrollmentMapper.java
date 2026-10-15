package org.unischeduler.enrollment.infrastructure.mapper;

import org.unischeduler.academic_catalog.domain.entity.AcademicPeriod;
import org.unischeduler.academic_catalog.domain.entity.AcademicProgram;
import org.unischeduler.enrollment.domain.entity.Enrollment;
import org.unischeduler.enrollment.domain.entity.EnrollmentDetail;
import org.unischeduler.enrollment.domain.entity.Student;
import org.unischeduler.enrollment.infrastructure.entity.EnrollmentEntity;

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
