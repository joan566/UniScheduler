package org.unischeduler.auth.infrastructure.repository.excel;

import org.unischeduler.backend.infrastructure.persistence.excel.core.ExcelDataStore;
import org.unischeduler.auth.infrastructure.entity.UserEntity;

import java.util.Optional;

import java.util.ArrayList;
import java.util.List;

public class ExcelUserRepository {

    private final ExcelDataStore store;

    public ExcelUserRepository(ExcelDataStore store) {
        this.store = store;
    }

    // =====================================================
    // 💾 SAVE
    // =====================================================
    public UserEntity save(UserEntity entity) {

        String id = generateId();
        entity.setUserId(id);

        store.getUsers().put(id, entity);

        return entity;
    }

    public UserEntity changePassword(UserEntity user) {
        store.getUsers().get(user.getUserId())
                .setPassword(user.getPassword());

        return store.getUsers().get(user.getUserId());


    }

    // =====================================================
    // 🔍 FIND BY EMAIL
    // =====================================================
    public Optional<UserEntity> findByEmail(String email) {

        for (UserEntity u : store.getUsers().values()) {

            if (email.equals(u.getEmail())) {
                return Optional.of(u);
            }
        }

        return Optional.empty();
    }

    // =====================================================
    // 🔍 EXISTS BY EMAIL
    // =====================================================
    public boolean existsByEmail(String email) {

        for (UserEntity u : store.getUsers().values()) {

            if (email.equals(u.getEmail())) {
                return true;
            }
        }

        return false;
    }

    // =====================================================
    // 🔍 EXISTS BY DOCUMENT NUMBER
    // =====================================================
    public boolean existsByDocumentNumber(String documentNumber) {

        for (UserEntity u : store.getUsers().values()) {

            if (documentNumber.equals(u.getDocumentNumber())) {
                return true;
            }
        }

        return false;
    }

    // =====================================================
    // 🔍 FIND BY ID
    // =====================================================
    public Optional<UserEntity> findById(String id) {

        return Optional.ofNullable(
                store.getUsers().get(id)
        );
    }

    // =====================================================
    // 🔍 FIND ALL
    // =====================================================
    public List<UserEntity> findAll() {

        return new ArrayList<>(
                store.getUsers().values()
        );
    }

    // =====================================================
    // ❌ DELETE
    // =====================================================
    public void delete(String id) {
        store.getUsers().remove(id);
    }

    // =====================================================
    // 🔢 ID GENERATOR
    // =====================================================
    private String generateId() {
        return String.valueOf(store.getUsers().size() + 1);
    }
}