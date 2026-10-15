package org.unischeduler.backend.infrastructure.persistence.excel.repository.auth;

import org.unischeduler.backend.infrastructure.entity.auth.UserRoleEntity;
import org.unischeduler.backend.infrastructure.persistence.excel.core.ExcelDataStore;

import java.util.Optional;

import java.util.List;

public class ExcelUserRoleRepository {

    private final ExcelDataStore dataStore;

    public ExcelUserRoleRepository(ExcelDataStore dataStore) {
        this.dataStore = dataStore;
    }

    public Optional<UserRoleEntity> findById(String id) {

        UserRoleEntity entity = dataStore.getUserRoles().get(id);

        return Optional.ofNullable(entity);
    }

    public List<UserRoleEntity> findByUserId(String userId) {

        return dataStore.getUserRoles()
                .values()
                .stream()
                .filter(ur -> ur.getUserId().equals(userId))
                .toList();
    }

    public List<UserRoleEntity> findByRoleId(String roleId) {

        return dataStore.getUserRoles()
                .values()
                .stream()
                .filter(ur -> ur.getRoleId().equals(roleId))
                .toList();
    }
}