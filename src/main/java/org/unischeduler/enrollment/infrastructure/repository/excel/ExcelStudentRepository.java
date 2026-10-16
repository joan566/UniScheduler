package org.unischeduler.enrollment.infrastructure.repository.excel;

import org.unischeduler.enrollment.infrastructure.entity.StudentEntity;
import org.unischeduler.shared.infrastructure.persistence.excel.core.ExcelDataStore;

import java.util.Optional;

import java.util.ArrayList;
import java.util.List;

public class ExcelStudentRepository {

    private final ExcelDataStore store;

    public ExcelStudentRepository(ExcelDataStore store) {
        this.store = store;
    }

    // =====================================================
    //  SAVE
    // =====================================================
    public StudentEntity save(StudentEntity entity) {

        Long id = generateId();
        entity.setStudentId(id);

        store.getStudents().put(id, entity);

        return entity;
    }

    // =====================================================
    // 🔍 FIND BY ID
    // =====================================================
    public Optional<StudentEntity> findById(Long id) {

        return Optional.ofNullable(
                store.getStudents().get(id)
        );
    }

    // =====================================================
    //  FIND ALL
    // =====================================================
    public List<StudentEntity> findAll() {

        return new ArrayList<>(
                store.getStudents().values()
        );
    }

    // =====================================================
    //  EXISTS BY STUDENT CODE
    // =====================================================
    public boolean existsByStudentCode(String code) {

        for (StudentEntity s : store.getStudents().values()) {

            if (code.equals(s.getStudentCode())) {
                return true;
            }
        }

        return false;
    }

    // =====================================================
    //  DELETE
    // =====================================================
    public void delete(Long id) {
        store.getStudents().remove(id);
    }

    public Optional<StudentEntity> findStudentByUserId(Long userId) {
        for (StudentEntity s : store.getStudents().values()) {
            if (userId.equals(s.getUserId())) {
                return Optional.of(s);
            }
        }

        return Optional.empty();
    }

    // =====================================================
    //  ID GENERATOR
    // =====================================================
    private Long generateId() {
        return store.getStudents().keySet().stream().mapToLong(Long::longValue).max().orElse(0L) + 1;
    }
}
