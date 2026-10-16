package org.unischeduler.academic_programming.domain.outputports;

import org.unischeduler.academic_programming.domain.entity.Group;

import java.util.List;
import java.util.Optional;

public interface GroupRepository {
    Optional<Group> findById(Long id);
    List<Group> findAll();
    Group save(Group group);
    Group update(Group group);
    boolean deleteById(Long id);
}
