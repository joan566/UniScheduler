package org.unischeduler.academic_catalog.infrastructure.repository.excel;

import org.unischeduler.academic_catalog.infrastructure.entity.AcademicProgramEntity;
import org.unischeduler.shared.infrastructure.persistence.excel.core.ExcelDataStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExcelAcademicProgramRepository {

    private final ExcelDataStore store;

    public ExcelAcademicProgramRepository(ExcelDataStore store) {
        this.store = store;
    }

    // =====================================================
    // 🔍 FIND BY ID
    // =====================================================
    public Optional<AcademicProgramEntity> findById(String id) {

        return Optional.ofNullable(
                store.getPrograms().get(id)
        );
    }

    public List<AcademicProgramEntity> findAll() {
        return new ArrayList<>(store.getPrograms().values());
    }
}
