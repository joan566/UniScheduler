package org.unischeduler.academic_programming.infrastructure.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unischeduler.academic_programming.infrastructure.entity.GroupScheduleEntity;

import java.util.List;

public interface SpringGroupScheduleRepository extends JpaRepository<GroupScheduleEntity, Long> {
    List<GroupScheduleEntity> findAllByGroupId(Long groupId);

    void deleteAllByGroupId(Long groupId);
}
