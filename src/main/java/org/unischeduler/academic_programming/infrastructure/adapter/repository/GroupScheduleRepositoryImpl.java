package org.unischeduler.academic_programming.infrastructure.adapter.repository;

import org.unischeduler.academic_programming.domain.entity.GroupSchedule;
import org.unischeduler.academic_programming.domain.outputports.GroupScheduleRepository;
import org.unischeduler.academic_programming.infrastructure.entity.GroupScheduleEntity;
import org.unischeduler.academic_programming.infrastructure.mapper.GroupScheduleMapper;
import org.unischeduler.academic_programming.infrastructure.repository.excel.ExcelGroupScheduleRepository;

import java.util.ArrayList;
import java.util.List;

public class GroupScheduleRepositoryImpl implements GroupScheduleRepository {
    private final ExcelGroupScheduleRepository groupScheduleRepository;

    public GroupScheduleRepositoryImpl(ExcelGroupScheduleRepository groupScheduleRepository) {
        this.groupScheduleRepository = groupScheduleRepository;
    }

    @Override
    public List<GroupSchedule> findAllWhereGroupId(Long groupId) {
        List<GroupScheduleEntity> entities = groupScheduleRepository.findAllWhereGroupId(groupId);

        List<GroupSchedule> groupSchedules = new ArrayList<>();
        for(GroupScheduleEntity e : entities) {
            GroupSchedule groupSchedule = GroupScheduleMapper.toDomain(e);
            groupSchedules.add(groupSchedule);
        }

        return groupSchedules;
    }

    @Override
    public GroupSchedule save(GroupSchedule groupSchedule) {
        GroupScheduleEntity entity = GroupScheduleMapper.toEntity(groupSchedule);
        GroupScheduleEntity entitySaved = groupScheduleRepository.save(entity);

        return GroupScheduleMapper.toDomain(entitySaved);
    }

    @Override
    public void deleteAllWhereGroupId(Long groupId) {
        groupScheduleRepository.deleteAllWhereGroupId(groupId);
    }
}
