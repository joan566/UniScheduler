package org.unischeduler.academic_programming.infrastructure.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unischeduler.academic_programming.infrastructure.entity.GroupEntity;

public interface SpringGroupRepository extends JpaRepository<GroupEntity, Long> {
}
