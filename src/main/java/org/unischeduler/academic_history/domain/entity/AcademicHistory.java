package org.unischeduler.academic_history.domain.entity;

import org.unischeduler.academic_catalog.domain.entity.Course;
import org.unischeduler.academic_history.domain.enums.AcademicHistoryCourseStatus;
import org.unischeduler.enrollment.domain.entity.Student;

import java.util.ArrayList;

public class AcademicHistory {

    //================// Atributos //================//
    private Long id;
    private Student student;
    private ArrayList<Course> completedCourses;
    private double grade;
    private AcademicHistoryCourseStatus status;


    //================// Constructores //================//

    public AcademicHistory(Long id, Student student, ArrayList<Course> completedCourses, double grade, AcademicHistoryCourseStatus status) {
        this.id = id;
        this.student = student;
        this.completedCourses = completedCourses;
        this.grade = grade;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public ArrayList<Course> getCompletedCourses() {
        return completedCourses;
    }

    public void setCompletedCourses(ArrayList<Course> completedCourses) {
        this.completedCourses = completedCourses;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public AcademicHistoryCourseStatus getStatus() {
        return status;
    }

    public void setStatus(AcademicHistoryCourseStatus status) {
        this.status = status;
    }

    //================// Funciones Adicionales //================//

}
