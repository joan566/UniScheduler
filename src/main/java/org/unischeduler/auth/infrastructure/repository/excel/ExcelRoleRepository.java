package org.unischeduler.auth.infrastructure.repository.excel;

import org.unischeduler.auth.infrastructure.entity.RoleEntity;
import org.unischeduler.backend.infrastructure.persistence.excel.core.ExcelDataStore;

import java.util.Optional;

public class ExcelRoleRepository {

    private final ExcelDataStore dataStore;

    public ExcelRoleRepository(ExcelDataStore dataStore) {
        this.dataStore = dataStore;
    }

    public Optional<RoleEntity> findById(String id) {

        RoleEntity role = dataStore.getRoles().get(id);

        return Optional.ofNullable(role);
    }
}
