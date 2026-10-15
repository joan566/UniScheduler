package org.unischeduler.academic_programming.infrastructure.repository.excel;

import org.unischeduler.academic_programming.infrastructure.entity.TeacherEntity;
import org.unischeduler.backend.infrastructure.persistence.excel.core.ExcelDataStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExcelTeacherRepository {

    private final ExcelDataStore store;

    public ExcelTeacherRepository(ExcelDataStore store) {
        this.store = store;
    }

    public List<TeacherEntity> findAll() {
        return new ArrayList<>(store.getTeachers().values());
    }

    // =====================================================
    // 🔍 FIND BY ID
    // =====================================================
    public Optional<TeacherEntity> findById(String id) {

        return Optional.ofNullable(
                store.getTeachers().get(id)
        );
    }
}