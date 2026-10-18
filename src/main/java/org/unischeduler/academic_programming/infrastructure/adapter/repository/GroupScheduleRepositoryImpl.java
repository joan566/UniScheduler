package org.unischeduler.academic_programming.infrastructure.adapter.repository;

import org.springframework.stereotype.Repository;
import org.unischeduler.academic_programming.domain.entity.GroupSchedule;
import org.unischeduler.academic_programming.domain.outputports.GroupScheduleRepository;
import org.unischeduler.academic_programming.infrastructure.entity.GroupScheduleEntity;
import org.unischeduler.academic_programming.infrastructure.mapper.GroupScheduleMapper;
import org.unischeduler.academic_programming.infrastructure.repository.postgres.SpringGroupScheduleRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GroupScheduleRepositoryImpl implements GroupScheduleRepository {
    private final SpringGroupScheduleRepository groupScheduleRepository;

    public GroupScheduleRepositoryImpl(SpringGroupScheduleRepository groupScheduleRepository) {
        this.groupScheduleRepository = groupScheduleRepository;
    }

    @Override
    public List<GroupSchedule> findAllWhereGroupId(Long groupId) {
        List<GroupScheduleEntity> entities = groupScheduleRepository.findAllByGroupId(groupId);

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
        groupScheduleRepository.deleteAllByGroupId(groupId);
    }
}
