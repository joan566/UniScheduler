package org.unischeduler.enrollment.infrastructure.mapper;

import org.unischeduler.academic_catalog.domain.entity.AcademicProgram;
import org.unischeduler.auth.domain.entity.User;
import org.unischeduler.enrollment.domain.entity.Student;
import org.unischeduler.enrollment.infrastructure.entity.StudentEntity;

public class StudentMapper {

    public static StudentEntity toEntity(Student student) {
        StudentEntity entity = new StudentEntity();

        entity.setId(
                student.getId()
        );

        entity.setStudentCode(
                student.getStudentCode()
        );

        entity.setUserId(
                student.getUser().getUserId()
        );

        entity.setAcademicProgramId(
                student.getAcademicProgram().getId()
        );

        return entity;
    }

    public static Student toDomain(
            StudentEntity entity,
            User user,
            AcademicProgram academicProgram
    ) {
        return new Student(
                entity.getId(),
                entity.getStudentCode(),
                user,
                academicProgram
        );
    }
}