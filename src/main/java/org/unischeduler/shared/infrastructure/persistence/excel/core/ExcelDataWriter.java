package org.unischeduler.shared.infrastructure.persistence.excel.core;


import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Table;
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

import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class ExcelDataWriter {

    private static final DateTimeFormatter TIME_FORMATTER =
            DateTimeFormatter.ofPattern("hh:mm:ss a");

    // =====================================================
    // 🚀 ENTRY POINT
    // =====================================================
    public void save(String filePath, ExcelDataStore store) {

        try {
            SpreadsheetDocument doc = SpreadsheetDocument.loadDocument(new File(filePath));

            clearDataRows(doc.getTableByName("AcademicProgram"));
            clearDataRows(doc.getTableByName("AcademicHistory"));
            clearDataRows(doc.getTableByName("Course"));
            clearDataRows(doc.getTableByName("AcademicPeriod"));
            clearDataRows(doc.getTableByName("Prerequisite"));

            clearDataRows(doc.getTableByName("Group"));
            clearDataRows(doc.getTableByName("Teachers"));
            clearDataRows(doc.getTableByName("GroupSchedule"));
            clearDataRows(doc.getTableByName("SemesterTemplate"));
            clearDataRows(doc.getTableByName("SemesterTemplateDetails"));

            clearDataRows(doc.getTableByName("Students"));
            clearDataRows(doc.getTableByName("Enrollments"));
            clearDataRows(doc.getTableByName("EnrollmentDetails"));

            clearDataRows(doc.getTableByName("Users"));
            clearDataRows(doc.getTableByName("Roles"));
            clearDataRows(doc.getTableByName("UserRole"));

            writePrograms(doc, store.getPrograms());
            writeHistories(doc, store.getHistories());
            writeCourses(doc, store.getCourses());
            writePeriods(doc, store.getPeriods());
            writePrerequisites(doc, store.getPrerequisites());

            writeGroups(doc, store.getGroups());
            writeTeachers(doc, store.getTeachers());
            writeGroupSchedules(doc, store.getGroupSchedules());
            writeTemplates(doc, store.getTemplates());
            writeTemplateDetails(doc, store.getTemplateDetails());

            writeStudents(doc, store.getStudents());
            writeEnrollments(doc, store.getEnrollments());
            writeEnrollmentDetails(doc, store.getEnrollmentDetails());

            writeUsers(doc, store.getUsers());
            writeRoles(doc, store.getRoles());
            writeUserRoles(doc, store.getUserRoles());

            doc.save(filePath);

        } catch (Exception e) {
            throw new RuntimeException("Error saving Excel data", e);
        }
    }

    private void clearDataRows(Table table) {

        while (table.getRowCount() > 1) {
            table.removeRowsByIndex(1, 1);
        }
    }

    // =====================================================
    //  CATALOG
    // =====================================================
    private void writeHistories(SpreadsheetDocument doc,
                               Map<Long, AcademicHistoryEntity> map) {

        Table table = doc.getTableByName("AcademicHistory");

        int row = 1;
        for (AcademicHistoryEntity e : map.values()) {

            ensureRow(table, row);
            for(int i = 0; i < e.getCompletedCoursesId().size(); i++) {
                table.getCellByPosition(0, row).setStringValue(String.valueOf(e.getHistoryId()));
                table.getCellByPosition(1, row).setStringValue(String.valueOf(e.getStudentId()));
                table.getCellByPosition(2, row).setStringValue(
                        String.valueOf(e.getCompletedCoursesId().get(i))
                );
                table.getCellByPosition(3, row).setDoubleValue(e.getGrade());
                table.getCellByPosition(4, row).setStringValue(e.getStatus());
            }

            row++;
        }
    }

    private void writePrograms(SpreadsheetDocument doc,
                               Map<Long, AcademicProgramEntity> map) {

        Table table = doc.getTableByName("AcademicProgram");

        int row = 1;
        for (AcademicProgramEntity e : map.values()) {

            ensureRow(table, row);

            table.getCellByPosition(0, row).setStringValue(String.valueOf(e.getAcademicProgramId()));
            table.getCellByPosition(1, row).setStringValue(e.getName());
            table.getCellByPosition(2, row).setStringValue(String.valueOf(e.getTotalSemesters()));

            row++;
        }
    }

    private void writeCourses(SpreadsheetDocument doc,
                              Map<Long, CourseEntity> map) {

        Table table = doc.getTableByName("Course");

        int row = 1;
        for (CourseEntity e : map.values()) {

            ensureRow(table, row);

            table.getCellByPosition(0, row).setStringValue(String.valueOf(e.getCourseId()));
            table.getCellByPosition(1, row).setStringValue(e.getName());
            table.getCellByPosition(2, row).setStringValue(e.getCode());
            table.getCellByPosition(3, row).setStringValue(String.valueOf(e.getCredits()));
            table.getCellByPosition(4, row).setStringValue(e.getDescription());

            row++;
        }
    }

    private void writePeriods(SpreadsheetDocument doc,
                              Map<Long, AcademicPeriodEntity> map) {

        Table table = doc.getTableByName("AcademicPeriod");

        int row = 1;
        for (AcademicPeriodEntity e : map.values()) {

            ensureRow(table, row);

            table.getCellByPosition(0, row).setStringValue(String.valueOf(e.getAcademicPeriodId()));
            table.getCellByPosition(1, row).setStringValue(e.getCode());
            table.getCellByPosition(2, row).setStringValue(e.getName());
            table.getCellByPosition(3, row).setStringValue(e.getStartDate());
            table.getCellByPosition(4, row).setStringValue(e.getEndDate());
            table.getCellByPosition(5, row).setStringValue(e.getStatus());

            row++;
        }
    }

    private void writePrerequisites(SpreadsheetDocument doc,
                                    Map<Long, PrerequisiteEntity> map) {

        Table table = doc.getTableByName("Prerequisite");

        int row = 1;
        for (PrerequisiteEntity e : map.values()) {

            ensureRow(table, row);

            table.getCellByPosition(0, row).setStringValue(String.valueOf(e.getPrerequisiteId()));
            table.getCellByPosition(1, row).setStringValue(String.valueOf(e.getCourseId()));
            table.getCellByPosition(2, row).setStringValue(String.valueOf(e.getPrerequisiteCourseId()));

            row++;
        }
    }

    // =====================================================
    // 🏫 PROGRAMMING
    // =====================================================

    private void writeGroups(SpreadsheetDocument doc,
                             Map<Long, GroupEntity> map) {

        Table table = doc.getTableByName("Group");

        int row = 1;
        for (GroupEntity e : map.values()) {

            ensureRow(table, row);

            table.getCellByPosition(0, row).setStringValue(String.valueOf(e.getGroupId()));
            table.getCellByPosition(1, row).setStringValue(String.valueOf(e.getCourseId()));
            table.getCellByPosition(2, row).setStringValue(String.valueOf(e.getTeacherId()));
            table.getCellByPosition(3, row).setStringValue(e.getGroupCode());
            table.getCellByPosition(4, row).setStringValue(String.valueOf(e.getCapacity()));

            row++;
        }
    }

    private void writeTeachers(SpreadsheetDocument doc,
                               Map<Long, TeacherEntity> map) {

        Table table = doc.getTableByName("Teachers");

        int row = 1;
        for (TeacherEntity e : map.values()) {

            ensureRow(table, row);

            table.getCellByPosition(0, row).setStringValue(String.valueOf(e.getTeacherId()));
            table.getCellByPosition(1, row).setStringValue(e.getName());

            row++;
        }
    }

    private void writeGroupSchedules(SpreadsheetDocument doc,
                                     Map<Long, GroupScheduleEntity> map) {

        Table table = doc.getTableByName("GroupSchedule");

        int row = 1;
        for (GroupScheduleEntity e : map.values()) {

            ensureRow(table, row);

            table.getCellByPosition(0, row).setStringValue(String.valueOf(e.getGroupScheduleId()));
            table.getCellByPosition(1, row).setStringValue(String.valueOf(e.getGroupId()));
            table.getCellByPosition(2, row).setStringValue(e.getDayOfWeek());

            table.getCellByPosition(3, row).setStringValue(e.getStartTime().format(TIME_FORMATTER));
            table.getCellByPosition(4, row).setStringValue(e.getEndTime().format(TIME_FORMATTER));

            table.getCellByPosition(5, row).setStringValue(e.getClassroom());

            row++;
        }
    }

    private void writeTemplates(SpreadsheetDocument doc,
                                Map<Long, SemesterTemplateEntity> map) {

        Table table = doc.getTableByName("SemesterTemplate");

        int row = 1;
        for (SemesterTemplateEntity e : map.values()) {

            ensureRow(table, row);

            table.getCellByPosition(0, row).setStringValue(String.valueOf(e.getTemplateId()));
            table.getCellByPosition(1, row).setStringValue(String.valueOf(e.getProgramId()));
            table.getCellByPosition(2, row).setStringValue(String.valueOf(e.getSemester()));

            row++;
        }
    }

    private void writeTemplateDetails(SpreadsheetDocument doc,
                                      Map<Long, SemesterTemplateDetailEntity> map) {

        Table table = doc.getTableByName("SemesterTemplateDetails");

        int row = 1;
        for (SemesterTemplateDetailEntity e : map.values()) {

            ensureRow(table, row);

            table.getCellByPosition(0, row).setStringValue(String.valueOf(e.getTemplateDetailId()));
            table.getCellByPosition(1, row).setStringValue(String.valueOf(e.getTemplateId()));
            table.getCellByPosition(2, row).setStringValue(String.valueOf(e.getGroupId()));

            row++;
        }
    }

    // =====================================================
    // 🧑‍🎓 ENROLLMENT
    // =====================================================

    private void writeStudents(SpreadsheetDocument doc,
                               Map<Long, StudentEntity> map) {

        Table table = doc.getTableByName("Students");

        int row = 1;
        for (StudentEntity e : map.values()) {

            ensureRow(table, row);

            table.getCellByPosition(0, row).setStringValue(String.valueOf(e.getStudentId()));
            table.getCellByPosition(1, row).setStringValue(e.getStudentCode());
            table.getCellByPosition(2, row).setStringValue(String.valueOf(e.getUserId()));
            table.getCellByPosition(3, row).setStringValue(String.valueOf(e.getAcademicProgramId()));

            row++;
        }
    }

    private void writeEnrollments(SpreadsheetDocument doc,
                                  Map<Long, EnrollmentEntity> map) {

        Table table = doc.getTableByName("Enrollments");

        int row = 1;
        for (EnrollmentEntity e : map.values()) {

            ensureRow(table, row);

            table.getCellByPosition(0, row).setStringValue(String.valueOf(e.getEnrollmentId()));
            table.getCellByPosition(1, row).setStringValue(String.valueOf(e.getStudentId()));
            table.getCellByPosition(2, row).setStringValue(String.valueOf(e.getAcademicProgramId()));
            table.getCellByPosition(3, row).setStringValue(e.getEnrollmentDate().toString());
            table.getCellByPosition(4, row).setStringValue(String.valueOf(e.getAcademicPeriodId()));

            row++;
        }
    }

    private void writeEnrollmentDetails(SpreadsheetDocument doc,
                                        Map<Long, EnrollmentDetailEntity> map) {

        Table table = doc.getTableByName("EnrollmentDetails");

        int row = 1;
        for (EnrollmentDetailEntity e : map.values()) {

            ensureRow(table, row);

            table.getCellByPosition(0, row).setStringValue(String.valueOf(e.getEnrollmentDetailId()));
            table.getCellByPosition(1, row).setStringValue(String.valueOf(e.getEnrollmentId()));
            table.getCellByPosition(2, row).setStringValue(String.valueOf(e.getGroupId()));
            table.getCellByPosition(3, row).setStringValue(e.getStatus());
            table.getCellByPosition(4, row).setStringValue(String.valueOf(e.getFinalGrade()));

            row++;
        }
    }

    // =====================================================
    // 🔐 AUTH
    // =====================================================

    private void writeUsers(SpreadsheetDocument doc,
                            Map<Long, UserEntity> map) {

        Table table = doc.getTableByName("Users");

        int row = 1;
        for (UserEntity e : map.values()) {

            ensureRow(table, row);

            table.getCellByPosition(0, row).setStringValue(String.valueOf(e.getUserId()));
            table.getCellByPosition(1, row).setStringValue(e.getFirstName());
            table.getCellByPosition(2, row).setStringValue(e.getLastName());
            table.getCellByPosition(3, row).setStringValue(String.valueOf(e.getDocumentType()));
            table.getCellByPosition(4, row).setStringValue(e.getDocumentNumber());
            table.getCellByPosition(5, row).setStringValue(String.valueOf(e.getBirthDate()));
            table.getCellByPosition(6, row).setStringValue(String.valueOf(e.getGender()));
            table.getCellByPosition(7, row).setStringValue(e.getPhoneNumber());
            table.getCellByPosition(8, row).setStringValue(e.getAddress());
            table.getCellByPosition(9, row).setStringValue(e.getEmail());
            table.getCellByPosition(10, row).setStringValue(e.getPassword());
            table.getCellByPosition(11, row).setStringValue(String.valueOf(e.getStatus()));
            table.getCellByPosition(13, row).setStringValue(String.valueOf(e.getCreatedAt()));

            row++;
        }
    }

    private void writeRoles(SpreadsheetDocument doc,
                            Map<Long, RoleEntity> map) {

        Table table = doc.getTableByName("Roles");

        int row = 1;
        for (RoleEntity e : map.values()) {

            ensureRow(table, row);

            table.getCellByPosition(0, row).setStringValue(String.valueOf(e.getRoleId()));
            table.getCellByPosition(1, row).setStringValue(e.getName());

            row++;
        }
    }

    private void writeUserRoles(SpreadsheetDocument doc,
                                Map<Long, UserRoleEntity> map) {

        Table table = doc.getTableByName("UserRole");

        int row = 1;
        for (UserRoleEntity e : map.values()) {

            ensureRow(table, row);

            table.getCellByPosition(0, row).setStringValue(String.valueOf(e.getUserRoleId()));
            table.getCellByPosition(1, row).setStringValue(String.valueOf(e.getUserId()));
            table.getCellByPosition(2, row).setStringValue(String.valueOf(e.getRoleId()));

            row++;
        }
    }

    // =====================================================
    // 🧰 UTIL
    // =====================================================

    private void ensureRow(Table table, int rowIndex) {
        if (rowIndex >= table.getRowCount()) {
            table.appendRow();
        }
    }
}