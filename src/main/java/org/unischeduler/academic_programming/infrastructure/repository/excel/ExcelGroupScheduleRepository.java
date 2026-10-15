package org.unischeduler.academic_programming.infrastructure.repository.excel;

import org.unischeduler.academic_programming.infrastructure.entity.GroupScheduleEntity;
import org.unischeduler.backend.infrastructure.persistence.excel.core.ExcelDataStore;

import java.util.List;

import java.util.ArrayList;

public class ExcelGroupScheduleRepository {

    private final ExcelDataStore store;

    public ExcelGroupScheduleRepository(ExcelDataStore store) {
        this.store = store;
    }

    public GroupScheduleEntity save(GroupScheduleEntity entity) {
        String id = generateId();
        entity.setGroupScheduleId(id);

        store.getGroupSchedules().put(id, entity);
        return entity;
    }

    public void deleteAllWhereGroupId(String groupId) {
        store.getGroupSchedules()
                .entrySet()
                .removeIf(entry -> groupId.equals(entry.getValue().getGroupId()));
    }

    // =====================================================
    // 🔍 FIND BY GROUP ID
    // =====================================================
    public List<GroupScheduleEntity> findAllWhereGroupId(String groupId) {

        List<GroupScheduleEntity> results = new ArrayList<>();

        for (GroupScheduleEntity s : store.getGroupSchedules().values()) {

            if (groupId.equals(s.getGroupId())) {
                results.add(s);
            }
        }

        return results;
    }

    // =====================================================
    // 🔍 FIND BY ID
    // =====================================================
    public GroupScheduleEntity findById(String id) {

        return store.getGroupSchedules().get(id);
    }

    // =====================================================
    // 🔍 FIND ALL (OPTIONAL)
    // =====================================================
    public List<GroupScheduleEntity> findAll() {

        return new ArrayList<>(
                store.getGroupSchedules().values()
        );
    }

    // =====================================================
    // ID GENERATOR
    // =====================================================
    private String generateId() {
        return String.valueOf(store.getGroupSchedules().size() + 1);
    }
}