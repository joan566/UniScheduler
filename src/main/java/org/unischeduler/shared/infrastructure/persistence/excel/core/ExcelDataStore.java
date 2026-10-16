package org.unischeduler.shared.infrastructure.persistence.excel.core;

import org.unischeduler.academic_catalog.infrastructure.entity.AcademicPeriodEntity;
import org.unischeduler.academic_catalog.infrastructure.entity.AcademicProgramEntity;
import org.unischeduler.academic_catalog.infrastructure.entity.CourseEntity;
import org.unischeduler.academic_catalog.infrastructure.entity.PrerequisiteEntity;
import org.unischeduler.academic_history.infrastructure.entity.AcademicHistoryEntity;
import org.unischeduler.academic_programming.infrastructure.entity.*;
import org.unischeduler.auth.infrastructure.entity.RoleEntity;
import org.unischeduler.auth.infrastructure.entity.UserEntity;
import org.unischeduler.auth.infrastructure.entity.UserRoleEntity;
import org.unischeduler.enrollment.infrastructure.entity.EnrollmentDetailEntity;
import org.unischeduler.enrollment.infrastructure.entity.EnrollmentEntity;
import org.unischeduler.enrollment.infrastructure.entity.StudentEntity;

import java.util.Map;

public class ExcelDataStore {

    private Map<Long, AcademicProgramEntity> programs;
    private Map<Long, CourseEntity> courses;
    private Map<Long, AcademicPeriodEntity> periods;
    private Map<Long, PrerequisiteEntity> prerequisites;

    private Map<Long, AcademicHistoryEntity> histories;

    private Map<Long, GroupEntity> groups;
    private Map<Long, TeacherEntity> teachers;
    private Map<Long, GroupScheduleEntity> groupSchedules;
    private Map<Long, SemesterTemplateEntity> templates;
    private Map<Long, SemesterTemplateDetailEntity> templateDetails;

    private Map<Long, StudentEntity> students;
    private Map<Long, EnrollmentEntity> enrollments;
    private Map<Long, EnrollmentDetailEntity> enrollmentDetails;

    private Map<Long, UserEntity> users;
    private Map<Long, RoleEntity> roles;
    private Map<Long, UserRoleEntity> userRoles;

    private boolean loaded = false;

    public void load(ExcelDataLoader loader, String filePath) {
        if (loaded) return;

        ExcelDataStore loadedStore = loader.load(filePath);

        this.programs = loadedStore.programs;
        this.courses = loadedStore.courses;
        this.periods = loadedStore.periods;
        this.prerequisites = loadedStore.prerequisites;

        this.histories = loadedStore.histories;

        this.groups = loadedStore.groups;
        this.teachers = loadedStore.teachers;
        this.groupSchedules = loadedStore.groupSchedules;
        this.templates = loadedStore.templates;
        this.templateDetails = loadedStore.templateDetails;

        this.students = loadedStore.students;
        this.enrollments = loadedStore.enrollments;
        this.enrollmentDetails = loadedStore.enrollmentDetails;

        this.users = loadedStore.users;
        this.roles = loadedStore.roles;
        this.userRoles = loadedStore.userRoles;

        loaded = true;
    }

    public Map<Long, AcademicProgramEntity> getPrograms() {
        return programs;
    }

    public void setPrograms(Map<Long, AcademicProgramEntity> programs) {
        this.programs = programs;
    }

    public Map<Long, CourseEntity> getCourses() {
        return courses;
    }

    public void setCourses(Map<Long, CourseEntity> courses) {
        this.courses = courses;
    }

    public Map<Long, AcademicPeriodEntity> getPeriods() {
        return periods;
    }

    public void setPeriods(Map<Long, AcademicPeriodEntity> periods) {
        this.periods = periods;
    }

    public Map<Long, PrerequisiteEntity> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(Map<Long, PrerequisiteEntity> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public Map<Long, GroupEntity> getGroups() {
        return groups;
    }

    public void setGroups(Map<Long, GroupEntity> groups) {
        this.groups = groups;
    }

    public Map<Long, TeacherEntity> getTeachers() {
        return teachers;
    }

    public void setTeachers(Map<Long, TeacherEntity> teachers) {
        this.teachers = teachers;
    }

    public Map<Long, GroupScheduleEntity> getGroupSchedules() {
        return groupSchedules;
    }

    public void setGroupSchedules(Map<Long, GroupScheduleEntity> groupSchedules) {
        this.groupSchedules = groupSchedules;
    }

    public Map<Long, SemesterTemplateEntity> getTemplates() {
        return templates;
    }

    public void setTemplates(Map<Long, SemesterTemplateEntity> templates) {
        this.templates = templates;
    }

    public Map<Long, SemesterTemplateDetailEntity> getTemplateDetails() {
        return templateDetails;
    }

    public void setTemplateDetails(Map<Long, SemesterTemplateDetailEntity> templateDetails) {
        this.templateDetails = templateDetails;
    }

    public Map<Long, StudentEntity> getStudents() {
        return students;
    }

    public void setStudents(Map<Long, StudentEntity> students) {
        this.students = students;
    }

    public Map<Long, EnrollmentEntity> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(Map<Long, EnrollmentEntity> enrollments) {
        this.enrollments = enrollments;
    }

    public Map<Long, EnrollmentDetailEntity> getEnrollmentDetails() {
        return enrollmentDetails;
    }

    public void setEnrollmentDetails(Map<Long, EnrollmentDetailEntity> enrollmentDetails) {
        this.enrollmentDetails = enrollmentDetails;
    }

    public Map<Long, UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Map<Long, UserEntity> users) {
        this.users = users;
    }

    public Map<Long, RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Map<Long, RoleEntity> roles) {
        this.roles = roles;
    }

    public Map<Long, UserRoleEntity> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Map<Long, UserRoleEntity> userRoles) {
        this.userRoles = userRoles;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public Map<Long, AcademicHistoryEntity> getHistories() {
        return histories;
    }

    public void setHistories(Map<Long, AcademicHistoryEntity> histories) {
        this.histories = histories;
    }
}