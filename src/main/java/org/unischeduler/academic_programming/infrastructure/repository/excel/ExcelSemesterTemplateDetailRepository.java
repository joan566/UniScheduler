package org.unischeduler.academic_programming.infrastructure.repository.excel;

import org.unischeduler.academic_programming.infrastructure.entity.SemesterTemplateDetailEntity;
import org.unischeduler.backend.infrastructure.persistence.excel.core.ExcelDataStore;

import java.util.ArrayList;

import java.util.List;

public class ExcelSemesterTemplateDetailRepository {

    private final ExcelDataStore store;

    public ExcelSemesterTemplateDetailRepository(ExcelDataStore store) {
        this.store = store;
    }

    // =====================================================
    // 🔍 FIND ALL BY TEMPLATE ID
    // =====================================================
    public List<SemesterTemplateDetailEntity> findAllWhereTemplateId(String templateId) {

        List<SemesterTemplateDetailEntity> results = new ArrayList<>();

        for (SemesterTemplateDetailEntity e : store.getTemplateDetails().values()) {

            if (templateId.equals(e.getTemplateId())) {
                results.add(e);
            }
        }

        return results;
    }
}