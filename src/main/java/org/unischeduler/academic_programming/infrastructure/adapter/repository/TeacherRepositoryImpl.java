package org.unischeduler.academic_programming.infrastructure.adapter.repository;

import org.unischeduler.academic_programming.domain.entity.Teacher;
import org.unischeduler.academic_programming.domain.outputports.TeacherRepository;
import org.unischeduler.academic_programming.infrastructure.entity.TeacherEntity;
import org.unischeduler.academic_programming.infrastructure.mapper.TeacherMapper;
import org.unischeduler.academic_programming.infrastructure.repository.excel.ExcelTeacherRepository;

import java.util.List;
import java.util.Optional;


public class TeacherRepositoryImpl implements TeacherRepository {
    private final ExcelTeacherRepository teacherRepository;

    public TeacherRepositoryImpl(ExcelTeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Optional<Teacher> findById(Long id) {
        Optional<TeacherEntity> entityOptional = teacherRepository.findById(id);
        if(entityOptional.isEmpty()) {
            return Optional.empty();
        }

        TeacherEntity entity = entityOptional.get();
        return Optional.of(TeacherMapper.toDomain(entity));
    }

    @Override
    public List<Teacher> findAll() {
        List<TeacherEntity> entities = teacherRepository.findAll();

        return entities
                .stream()
                .map(TeacherMapper::toDomain)
                .toList();
    }
}
