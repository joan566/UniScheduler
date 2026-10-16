package org.unischeduler.academic_programming.infrastructure.adapter.repository;

import org.unischeduler.shared.domain.exceptions.EntityNotFoundException;
import org.unischeduler.academic_catalog.domain.entity.Course;
import org.unischeduler.academic_programming.domain.entity.Group;
import org.unischeduler.academic_programming.domain.entity.GroupSchedule;
import org.unischeduler.academic_programming.domain.entity.Teacher;
import org.unischeduler.academic_catalog.domain.outputports.CourseRepository;
import org.unischeduler.academic_programming.domain.outputports.GroupRepository;
import org.unischeduler.academic_programming.domain.outputports.GroupScheduleRepository;
import org.unischeduler.academic_programming.domain.outputports.TeacherRepository;
import org.unischeduler.academic_programming.infrastructure.entity.GroupEntity;
import org.unischeduler.academic_programming.infrastructure.mapper.GroupMapper;
import org.unischeduler.academic_programming.infrastructure.repository.excel.ExcelGroupRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GroupRepositoryImpl implements GroupRepository {
    private final ExcelGroupRepository groupRepository;
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final GroupScheduleRepository groupScheduleRepository;

    public GroupRepositoryImpl(ExcelGroupRepository groupRepository, CourseRepository courseRepository, TeacherRepository teacherRepository, GroupScheduleRepository groupScheduleRepository) {
        this.groupRepository = groupRepository;
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
        this.groupScheduleRepository = groupScheduleRepository;
    }

    @Override
    public Optional<Group> findById(Long id) {
        Optional<GroupEntity> entityOptional = groupRepository.findById(id);
        return entityOptional.map(this::toDomain);

    }

    @Override
    public List<Group> findAll() {
        List<GroupEntity> entities = groupRepository.findAll();

        List<Group> groups = new ArrayList<>();
        for(GroupEntity entity : entities) {
            groups.add(toDomain(entity));
        }

        return groups;
    }

    @Override
    public Group save(Group group) {
        GroupEntity entitySaved = groupRepository.save(GroupMapper.toEntity(group));
        return toDomain(entitySaved);
    }

    @Override
    public Group update(Group group) {
        GroupEntity entityUpdated = groupRepository.update(GroupMapper.toEntity(group));
        return toDomain(entityUpdated);
    }

    @Override
    public boolean deleteById(Long id) {
        return groupRepository.deleteById(id);
    }

    private Group toDomain(GroupEntity entity) {
        Course course = courseRepository.findById(entity.getCourseId())
                .orElseThrow(() -> new EntityNotFoundException("No existe el courso con id: " + entity.getCourseId()));
        Teacher teacher = teacherRepository.findById(entity.getTeacherId())
                .orElseThrow(() -> new EntityNotFoundException("No existe el profesor con id: " + entity.getTeacherId()));
        List<GroupSchedule> groupSchedules = groupScheduleRepository.findAllWhereGroupId(entity.getGroupId());

        return GroupMapper.toDomain(entity, course, teacher, groupSchedules);
    }
}
