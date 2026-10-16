package org.unischeduler.academic_history.infrastructure.repository.excel;

import org.unischeduler.academic_history.infrastructure.entity.AcademicHistoryEntity;
import org.unischeduler.shared.infrastructure.persistence.excel.core.ExcelDataStore;

import java.util.Optional;

public class ExcelAcademicHistoryRepository {
    private final ExcelDataStore store;

    public ExcelAcademicHistoryRepository(ExcelDataStore store) {
        this.store = store;
    }

    public Optional<AcademicHistoryEntity> findByStudentId(Long studentId) {
        for(AcademicHistoryEntity entity : store.getHistories().values()) {
            if(studentId.equals(entity.getStudentId())) {
                return Optional.of(entity);
            }
        }

        return Optional.empty();
    }

    public AcademicHistoryEntity save(AcademicHistoryEntity entity) {
        Long id = generateId();
        entity.setHistoryId(id);

        store.getHistories().put(id, entity);

        return entity;
    }

    // =====================================================
    // 🔢 ID GENERATOR
    // =====================================================
    private Long generateId() {
        return store.getHistories().keySet().stream().mapToLong(Long::longValue).max().orElse(0L) + 1;
    }
}
