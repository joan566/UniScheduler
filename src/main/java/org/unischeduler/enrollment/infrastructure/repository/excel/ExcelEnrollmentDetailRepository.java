package org.unischeduler.enrollment.infrastructure.repository.excel;

import org.unischeduler.enrollment.infrastructure.entity.EnrollmentDetailEntity;
import org.unischeduler.shared.infrastructure.persistence.excel.core.ExcelDataStore;

import java.util.ArrayList;

import java.util.List;

public class ExcelEnrollmentDetailRepository {

    private final ExcelDataStore store;

    public ExcelEnrollmentDetailRepository(ExcelDataStore store) {
        this.store = store;
    }

    // =====================================================
    // 💾 SAVE (IN MEMORY)
    // =====================================================
    public EnrollmentDetailEntity save(EnrollmentDetailEntity detail) {

        Long id = generateId();
        detail.setEnrollmentDetailId(id);

        store.getEnrollmentDetails().put(id, detail);

        return detail;
    }

    // =====================================================
    // 🔍 FIND BY ENROLLMENT ID (LINEAR SEARCH)
    // =====================================================
    public List<EnrollmentDetailEntity> findByEnrollmentId(Long enrollmentId) {

        List<EnrollmentDetailEntity> results = new ArrayList<>();

        for (EnrollmentDetailEntity detail : store.getEnrollmentDetails().values()) {

            if (detail.getEnrollmentId() != null &&
                    detail.getEnrollmentId().equals(enrollmentId)) {

                results.add(detail);
            }
        }

        return results;
    }

    // =====================================================
    // ⚙️ DELETE (OPTIONAL BUT CONSISTENT)
    // =====================================================
    public void delete(Long enrollmentDetailId) {
        store.getEnrollmentDetails().remove(enrollmentDetailId);
    }

    // =====================================================
    // 🧠 FIND BY ID
    // =====================================================
    public EnrollmentDetailEntity findById(Long id) {
        return store.getEnrollmentDetails().get(id);
    }

    // =====================================================
    // 🔢 ID GENERATOR
    // =====================================================
    private Long generateId() {
        return store.getEnrollmentDetails().keySet().stream().mapToLong(Long::longValue).max().orElse(0L) + 1;
    }
}