package org.unischeduler.enrollment.domain.entity;

import org.unischeduler.academic_catalog.domain.entity.AcademicProgram;
import org.unischeduler.auth.domain.entity.User;

public class Student {

    //================// Atributos //================//
    private final Long id;
    private final String studentCode;
    private final User user;
    private final AcademicProgram academicProgram;


    //================// Constructores //================//
    public Student(Long id, String studentCode, User user, AcademicProgram academicProgram) {
        this.id = id;
        this.studentCode = studentCode;
        this.user = user;
        this.academicProgram = academicProgram;
    }

    //================// Setters y Getters //================//

    public Long getId() {
        return id;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public User getUser() {
        return user;
    }

    public AcademicProgram getAcademicProgram() {
        return academicProgram;
    }

}
