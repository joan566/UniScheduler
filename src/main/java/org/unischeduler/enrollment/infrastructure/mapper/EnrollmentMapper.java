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

        entity.setId(
                enrollment.getId()
        );

        entity.setStudentId(
                enrollment.getStudent().getId()
        );

        entity.setAcademicProgramId(
                enrollment.getAcademicProgram().getId()
        );

        entity.setEnrollmentDate(
                enrollment.getEnrollmentDate()
        );


        entity.setAcademicPeriodId(enrollment.getAcademicPeriod().getId());

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
                entity.getId(),
                student,
                academicProgram,
                entity.getEnrollmentDate(),
                details,
                academicPeriod
        );
    }
}
