package org.unischeduler.enrollment.domain.entity;

import org.unischeduler.academic_catalog.domain.entity.AcademicPeriod;
import org.unischeduler.academic_catalog.domain.entity.AcademicProgram;

import java.time.LocalDate;
import java.util.List;

public class Enrollment {

    //================// Atributos //================//
    private final Long enrollmentId;
    private final Student student;
    private  AcademicProgram academicProgram;
    private final LocalDate enrollmentDate;
    private List<EnrollmentDetail> details;
    private AcademicPeriod academicPeriod;


    public Enrollment(Long enrollmentId, Student student, AcademicProgram academicProgram,
                      LocalDate enrollmentDate, List<EnrollmentDetail> details,
                      AcademicPeriod academicPeriod) {
        this.enrollmentId = enrollmentId;
        this.student = student;
        this.academicProgram = academicProgram;
        this.enrollmentDate = enrollmentDate;
        this.details = details;
        this.academicPeriod = academicPeriod;
    }

    //================// Setters y Getters //================//

    public Long getEnrollmentId() {
        return enrollmentId;
    }

    public Student getStudent() {
        return student;
    }


    public AcademicProgram getAcademicProgram() {
        return academicProgram;
    }

    public void setAcademicProgram(AcademicProgram academicProgram) {
        this.academicProgram = academicProgram;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public List<EnrollmentDetail> getDetails() {
        return details;
    }

    public void setDetails(List<EnrollmentDetail> details) {
        this.details = details;
    }

    public AcademicPeriod getAcademicPeriod() {
        return academicPeriod;
    }

    public void setAcademicPeriod(AcademicPeriod academicPeriod) {
        this.academicPeriod = academicPeriod;
    }

    //================// Funciones Adicionales //================//


}
