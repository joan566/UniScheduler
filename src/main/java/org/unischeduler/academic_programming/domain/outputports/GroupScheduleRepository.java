package org.unischeduler.academic_programming.domain.outputports;

import org.unischeduler.academic_programming.domain.entity.GroupSchedule;

import java.util.List;

public interface GroupScheduleRepository {
    List<GroupSchedule> findAllWhereGroupId(Long groupId);
    GroupSchedule save(GroupSchedule groupSchedule);
    void deleteAllWhereGroupId(Long groupId);
}
