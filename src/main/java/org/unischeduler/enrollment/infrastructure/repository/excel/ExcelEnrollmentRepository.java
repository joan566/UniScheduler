package org.unischeduler.enrollment.infrastructure.repository.excel;

import org.unischeduler.enrollment.infrastructure.entity.EnrollmentEntity;
import org.unischeduler.shared.infrastructure.persistence.excel.core.ExcelDataStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExcelEnrollmentRepository {

    private final ExcelDataStore store;

    public ExcelEnrollmentRepository(ExcelDataStore store) {
        this.store = store;
    }

    // =====================================================
    // 💾 SAVE (IN MEMORY)
    // =====================================================
    public EnrollmentEntity save(EnrollmentEntity entity) {

        Long id = generateId();
        entity.setEnrollmentId(id);

        store.getEnrollments().put(id, entity);

        return entity;
    }

    // =====================================================
    // 🔍 FIND BY ID
    // =====================================================
    public EnrollmentEntity findById(Long id) {
        return store.getEnrollments().get(id);
    }

    // =====================================================
    // 🔍 FIND ALL
    // =====================================================
    public List<EnrollmentEntity> findAll() {
        return new ArrayList<>(store.getEnrollments().values());
    }

    // =====================================================
    // ❌ DELETE
    // =====================================================
    public void delete(Long id) {
        store.getEnrollments().remove(id);
    }

    public Optional<EnrollmentEntity> findByStudentAndActivePeriod(Long studentId, Long periodId) {
        for(EnrollmentEntity e : store.getEnrollments().values()) {
            if(studentId.equals(e.getStudentId()) && periodId.equals(e.getAcademicPeriodId())) {
                return Optional.of(e);
            }
        }

        return Optional.empty();
    }

    public List<EnrollmentEntity> findAllWhereStudentId(Long studentId) {
        return store.getEnrollments()
                .values()
                .stream()
                .filter(e -> studentId.equals(e.getStudentId()))
                .toList();
    }

    // =====================================================
    // 🔢 ID GENERATOR
    // =====================================================
    private Long generateId() {
        return store.getEnrollments().keySet().stream().mapToLong(Long::longValue).max().orElse(0L) + 1;
    }
}