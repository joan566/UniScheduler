package org.unischeduler.academic_programming.infrastructure.repository.excel;

import org.unischeduler.academic_programming.infrastructure.entity.GroupEntity;
import org.unischeduler.shared.infrastructure.persistence.excel.core.ExcelDataStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExcelGroupRepository {

    private final ExcelDataStore store;

    public ExcelGroupRepository(ExcelDataStore store) {
        this.store = store;
    }

    // =====================================================
    // 💾 SAVE
    // =====================================================
    public GroupEntity save(GroupEntity entity) {

        Long id = generateId();
        entity.setGroupId(id);

        store.getGroups().put(id, entity);

        return entity;
    }

    // =====================================================
    // 🔍 FIND BY ID
    // =====================================================
    public Optional<GroupEntity> findById(Long id) {

        return Optional.ofNullable(
                store.getGroups().get(id)
        );
    }

    // =====================================================
    // 🔍 FIND ALL
    // =====================================================
    public List<GroupEntity> findAll() {

        return new ArrayList<>(
                store.getGroups().values()
        );
    }

    // =====================================================
    // ❌ DELETE BY ID
    // =====================================================
    public boolean deleteById(Long id) {

        return store.getGroups().remove(id) != null;
    }

    // =====================================================
    // ✏️ UPDATE
    // =====================================================
    public GroupEntity update(GroupEntity entity) {

        if (!store.getGroups().containsKey(entity.getGroupId())) {
            throw new RuntimeException(
                    "Group not found with id: " + entity.getGroupId()
            );
        }

        store.getGroups().put(entity.getGroupId(), entity);

        return entity;
    }

    // =====================================================
    // 🔢 ID GENERATOR
    // =====================================================
    private Long generateId() {
        return store.getGroups().keySet().stream().mapToLong(Long::longValue).max().orElse(0L) + 1;
    }
}
