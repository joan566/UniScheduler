package org.unischeduler.shared.infrastructure.persistence.excel.core;

import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Table;
import org.unischeduler.academic_catalog.infrastructure.entity.AcademicPeriodEntity;
import org.unischeduler.academic_catalog.infrastructure.entity.AcademicProgramEntity;
import org.unischeduler.academic_catalog.infrastructure.entity.CourseEntity;
import org.unischeduler.academic_catalog.infrastructure.entity.PrerequisiteEntity;
import org.unischeduler.academic_history.infrastructure.entity.AcademicHistoryEntity;
import org.unischeduler.academic_programming.infrastructure.entity.*;
import org.unischeduler.auth.domain.enums.DocumentType;
import org.unischeduler.auth.domain.enums.Gender;
import org.unischeduler.auth.domain.enums.Status;
import org.unischeduler.auth.infrastructure.entity.RoleEntity;
import org.unischeduler.auth.infrastructure.entity.UserEntity;
import org.unischeduler.auth.infrastructure.entity.UserRoleEntity;
import org.unischeduler.enrollment.infrastructure.entity.EnrollmentDetailEntity;
import org.unischeduler.enrollment.infrastructure.entity.EnrollmentEntity;
import org.unischeduler.enrollment.infrastructure.entity.StudentEntity;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelDataLoader {

    public ExcelDataStore load(String filePath) {
        try {
            SpreadsheetDocument doc = SpreadsheetDocument.loadDocument(new File(filePath));

            ExcelDataStore store = new ExcelDataStore();

            store.setPrograms(readPrograms(doc));
            store.setCourses(readCourses(doc));
            store.setPeriods(readPeriods(doc));
            store.setPrerequisites(readPrerequisites(doc));

            store.setHistories(readHistories(doc));

            store.setGroups(readGroups(doc));
            store.setTeachers(readTeachers(doc));
            store.setGroupSchedules(readGroupSchedules(doc));
            store.setTemplates(readTemplates(doc));
            store.setTemplateDetails(readTemplateDetails(doc));

            store.setStudents(readStudents(doc));
            store.setEnrollments(readEnrollments(doc));
            store.setEnrollmentDetails(readEnrollmentDetails(doc));

            store.setUsers(readUsers(doc));
            store.setRoles(readRoles(doc));
            store.setUserRoles(readUserRoles(doc));

            store.setLoaded(true);

            return store;
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    private Map<Long, AcademicHistoryEntity> readHistories(SpreadsheetDocument doc) {
        Map<Long, AcademicHistoryEntity> map = new HashMap<>();
        Table table = doc.getTableByName("AcademicHistory");

        for (int i = 1; i < table.getRowCount(); i++) {
            if (!hasId(table, i)) continue;

            AcademicHistoryEntity e = new AcademicHistoryEntity();

            e.setHistoryId(readId(table, 0, i));
            e.setStudentId(readId(table, 1, i));

            List<Long> completedCourses = new ArrayList<>();
            for(int j = 1; j < table.getRowCount(); j++) {
                if (!hasId(table, j)) continue;

                Long studentId = readId(table, 1, j);
                Long courseId = readId(table, 2, j);
                if (studentId != null && studentId.equals(e.getStudentId()) && courseId != null) {
                    completedCourses.add(courseId);
                }
            }

            e.setCompletedCourses(completedCourses);
            e.setGrade(Double.parseDouble(table.getCellByPosition(3, i).getStringValue()));
            e.setStatus(table.getCellByPosition(4, i).getStringValue());

            map.put(e.getHistoryId(), e);
        }

        return map;
    }

    // =====================================================
    // 📚 CATALOG
    // =====================================================

    private Map<Long, AcademicProgramEntity> readPrograms(SpreadsheetDocument doc) {

        Map<Long, AcademicProgramEntity> map = new HashMap<>();
        Table table = doc.getTableByName("AcademicProgram");

        for (int i = 1; i < table.getRowCount(); i++) {
            if (!hasId(table, i)) continue;

            AcademicProgramEntity e = new AcademicProgramEntity();

            e.setAcademicProgramId(readId(table, 0, i));
            e.setName(table.getCellByPosition(1, i).getStringValue());
            e.setTotalSemesters(Integer.parseInt(
                    table.getCellByPosition(2, i).getStringValue()
            ));


            map.put(e.getAcademicProgramId(), e);
        }

        return map;
    }

    private Map<Long, CourseEntity> readCourses(SpreadsheetDocument doc) {

        Map<Long, CourseEntity> map = new HashMap<>();
        Table table = doc.getTableByName("Course");

        for (int i = 1; i < table.getRowCount(); i++) {
            if (!hasId(table, i)) continue;

            CourseEntity e = new CourseEntity();

            e.setCourseId(readId(table, 0, i));
            e.setName(table.getCellByPosition(1, i).getStringValue());
            e.setCode(table.getCellByPosition(2, i).getStringValue());
            e.setCredits(Integer.parseInt(
                    table.getCellByPosition(3, i).getStringValue()
            ));

            map.put(e.getCourseId(), e);
        }

        return map;
    }

    private Map<Long, AcademicPeriodEntity> readPeriods(SpreadsheetDocument doc) {

        Map<Long, AcademicPeriodEntity> map = new HashMap<>();
        Table table = doc.getTableByName("AcademicPeriod");

        for (int i = 1; i < table.getRowCount(); i++) {
            if (!hasId(table, i)) continue;

            AcademicPeriodEntity e = new AcademicPeriodEntity();

            e.setAcademicPeriodId(readId(table, 0, i));
            e.setCode(table.getCellByPosition(1, i).getStringValue());
            e.setName(table.getCellByPosition(2, i).getStringValue());
            e.setStartDate(table.getCellByPosition(3, i).getStringValue());
            e.setEndDate(table.getCellByPosition(4, i).getStringValue());
            e.setStatus(table.getCellByPosition(5, i).getStringValue());

            map.put(e.getAcademicPeriodId(), e);
        }

        return map;
    }

    private Map<Long, PrerequisiteEntity> readPrerequisites(SpreadsheetDocument doc) {

        Map<Long, PrerequisiteEntity> map = new HashMap<>();
        Table table = doc.getTableByName("Prerequisite");

        for (int i = 1; i < table.getRowCount(); i++) {
            if (!hasId(table, i)) continue;

            PrerequisiteEntity e = new PrerequisiteEntity();

            e.setPrerequisiteId(readId(table, 0, i));
            e.setCourseId(readId(table, 1, i));
            e.setPrerequisiteCourseId(readId(table, 2, i));

            map.put(e.getPrerequisiteId(), e);
        }

        return map;
    }

    // =====================================================
    // 🏫 PROGRAMMING
    // =====================================================

    private Map<Long, GroupEntity> readGroups(SpreadsheetDocument doc) {

        Map<Long, GroupEntity> map = new HashMap<>();
        Table table = doc.getTableByName("Group");

        for (int i = 1; i < table.getRowCount(); i++) {
            if (!hasId(table, i)) continue;

            GroupEntity g = new GroupEntity();

            g.setGroupId(readId(table, 0, i));
            g.setCourseId(readId(table, 1, i));
            g.setTeacherId(readId(table, 2, i));
            g.setGroupCode(table.getCellByPosition(3, i).getStringValue());
            g.setCapacity(Integer.parseInt(
                    table.getCellByPosition(4, i).getStringValue()
            ));

            map.put(g.getGroupId(), g);
        }

        System.out.println("TOTAL GROUPS LOADED: " + map.size());

        return map;
    }

    private Map<Long, TeacherEntity> readTeachers(SpreadsheetDocument doc) {

        Map<Long, TeacherEntity> map = new HashMap<>();
        Table table = doc.getTableByName("Teachers");

        for (int i = 1; i < table.getRowCount(); i++) {
            if (!hasId(table, i)) continue;

            TeacherEntity t = new TeacherEntity();

            t.setTeacherId(readId(table, 0, i));
            t.setName(table.getCellByPosition(1, i).getStringValue());

            map.put(t.getTeacherId(), t);
        }

        return map;
    }

    private Map<Long, GroupScheduleEntity> readGroupSchedules(SpreadsheetDocument doc) {

        Map<Long, GroupScheduleEntity> map = new HashMap<>();
        Table table = doc.getTableByName("GroupSchedule");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a");

        for (int i = 1; i < table.getRowCount(); i++) {
            if (!hasId(table, i)) continue;

            GroupScheduleEntity e = new GroupScheduleEntity();

            e.setGroupScheduleId(readId(table, 0, i));
            e.setGroupId(readId(table, 1, i));
            e.setDayOfWeek(table.getCellByPosition(2, i).getStringValue());

            String startStr = table.getCellByPosition(3, i).getStringValue();
            String endStr = table.getCellByPosition(4, i).getStringValue();


            e.setStartTime(LocalTime.parse(startStr, timeFormatter));
            e.setEndTime(LocalTime.parse(endStr, timeFormatter));
            e.setClassroom(table.getCellByPosition(5, i).getStringValue());

            map.put(e.getGroupScheduleId(), e);
        }

        return map;
    }

    private Map<Long, SemesterTemplateEntity> readTemplates(SpreadsheetDocument doc) {

        Map<Long, SemesterTemplateEntity> map = new HashMap<>();
        Table table = doc.getTableByName("SemesterTemplate");

        for (int i = 1; i < table.getRowCount(); i++) {
            if (!hasId(table, i)) continue;

            SemesterTemplateEntity e = new SemesterTemplateEntity();

            e.setTemplateId(readId(table, 0, i));
            e.setProgramId(readId(table, 1, i));
            e.setSemester(Integer.parseInt(
                    table.getCellByPosition(2, i).getStringValue()
            ));

            map.put(e.getTemplateId(), e);
        }

        return map;
    }

    private Map<Long, SemesterTemplateDetailEntity> readTemplateDetails(SpreadsheetDocument doc) {

        Map<Long, SemesterTemplateDetailEntity> map = new HashMap<>();
        Table table = doc.getTableByName("SemesterTemplateDetails");

        for (int i = 1; i < table.getRowCount(); i++) {
            if (!hasId(table, i)) continue;

            SemesterTemplateDetailEntity e = new SemesterTemplateDetailEntity();

            e.setTemplateDetailId(readId(table, 0, i));
            e.setTemplateId(readId(table, 1, i));
            e.setGroupId(readId(table, 2, i));

            map.put(e.getTemplateDetailId(), e);
        }

        return map;
    }

    // =====================================================
    // 🧑‍🎓 ENROLLMENT
    // =====================================================

    private Map<Long, StudentEntity> readStudents(SpreadsheetDocument doc) {

        Map<Long, StudentEntity> map = new HashMap<>();
        Table table = doc.getTableByName("Students");

        for (int i = 1; i < table.getRowCount(); i++) {
            if (!hasId(table, i)) continue;

            StudentEntity s = new StudentEntity();

            s.setStudentId(readId(table, 0, i));
            s.setStudentCode(table.getCellByPosition(1, i).getStringValue());
            s.setUserId(readId(table, 2, i));
            s.setAcademicProgramId(readId(table, 3, i));

            map.put(s.getStudentId(), s);
        }

        return map;
    }

    private Map<Long, EnrollmentEntity> readEnrollments(SpreadsheetDocument doc) {

        Map<Long, EnrollmentEntity> map = new HashMap<>();
        Table table = doc.getTableByName("Enrollments");

        for (int i = 1; i < table.getRowCount(); i++) {
            if (!hasId(table, i)) continue;

            EnrollmentEntity e = new EnrollmentEntity();

            e.setEnrollmentId(readId(table, 0, i));
            e.setStudentId(readId(table, 1, i));
            e.setAcademicProgramId(readId(table, 2, i));
            e.setEnrollmentDate(LocalDate.parse(table.getCellByPosition(3, i).getStringValue()));
            e.setAcademicPeriodId(readId(table, 4, i));

            map.put(e.getEnrollmentId(), e);
        }

        return map;
    }

    private Map<Long, EnrollmentDetailEntity> readEnrollmentDetails(SpreadsheetDocument doc) {

        Map<Long, EnrollmentDetailEntity> map = new HashMap<>();
        Table table = doc.getTableByName("EnrollmentDetails");

        for (int i = 1; i < table.getRowCount(); i++) {
            if (!hasId(table, i)) continue;

            EnrollmentDetailEntity e = new EnrollmentDetailEntity();

            e.setEnrollmentDetailId(readId(table, 0, i));
            e.setEnrollmentId(readId(table, 1, i));
            e.setGroupId(readId(table, 2, i));
            e.setStatus(table.getCellByPosition(3, i).getStringValue());
            e.setFinalGrade(Double.parseDouble(
                    table.getCellByPosition(4, i).getStringValue()
            ));

            map.put(e.getEnrollmentDetailId(), e);
        }

        return map;
    }

    // =====================================================
    // 🔐 AUTH
    // =====================================================

    private Map<Long, UserEntity> readUsers(SpreadsheetDocument doc) {

        Map<Long, UserEntity> map = new HashMap<>();
        Table table = doc.getTableByName("Users");

        for (int i = 1; i < table.getRowCount(); i++) {
            if (!hasId(table, i)) continue;

            UserEntity u = new UserEntity();

            u.setUserId(readId(table, 0, i));
            u.setFirstName(table.getCellByPosition(1, i).getStringValue());
            u.setLastName(table.getCellByPosition(2, i).getStringValue());
            u.setDocumentType(DocumentType.valueOf(table.getCellByPosition(3, i).getStringValue()));
            u.setDocumentNumber(table.getCellByPosition(4, i).getStringValue());
            u.setBirthDate(LocalDate.parse(table.getCellByPosition(5, i).getStringValue()));
            u.setGender(Gender.valueOf(table.getCellByPosition(6, i).getStringValue()));
            u.setPhoneNumber(table.getCellByPosition(7, i).getStringValue());
            u.setAddress(table.getCellByPosition(8, i).getStringValue());
            u.setEmail(table.getCellByPosition(9, i).getStringValue());
            u.setPassword(table.getCellByPosition(10, i).getStringValue());
            u.setStatus(Status.valueOf(table.getCellByPosition(11, i).getStringValue()));
            u.setCreatedAt(LocalDateTime.parse(table.getCellByPosition(13, i).getStringValue()));

            map.put(u.getUserId(), u);
        }

        return map;
    }

    private Map<Long, RoleEntity> readRoles(SpreadsheetDocument doc) {

        Map<Long, RoleEntity> map = new HashMap<>();
        Table table = doc.getTableByName("Roles");

        for (int i = 1; i < table.getRowCount(); i++) {
            if (!hasId(table, i)) continue;

            RoleEntity r = new RoleEntity();

            r.setRoleId(readId(table, 0, i));
            r.setName(table.getCellByPosition(1, i).getStringValue());

            map.put(r.getRoleId(), r);
        }

        return map;
    }

    private Map<Long, UserRoleEntity> readUserRoles(SpreadsheetDocument doc) {

        Map<Long, UserRoleEntity> map = new HashMap<>();
        Table table = doc.getTableByName("UserRole");

        for (int i = 1; i < table.getRowCount(); i++) {
            if (!hasId(table, i)) continue;

            UserRoleEntity ur = new UserRoleEntity();

            ur.setUserRoleId(readId(table, 0, i));
            ur.setUserId(readId(table, 1, i));
            ur.setRoleId(readId(table, 2, i));

            map.put(ur.getUserRoleId(), ur);
        }

        return map;
    }
    private boolean hasId(Table table, int row) {
        String value = table.getCellByPosition(0, row).getStringValue();
        return value != null && !value.isBlank();
    }

    private Long readId(Table table, int column, int row) {
        String cellValue = table.getCellByPosition(column, row).getStringValue();
        if (cellValue == null || cellValue.isBlank()) {
            return null;
        }
        String value = cellValue.trim();
        java.util.regex.Matcher matcher = java.util.regex.Pattern
                .compile("(?:[A-Za-z]+)?(\\d+)")
                .matcher(value);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid numeric ID: " + value);
        }
        return Long.parseLong(matcher.group(1));
    }

}
