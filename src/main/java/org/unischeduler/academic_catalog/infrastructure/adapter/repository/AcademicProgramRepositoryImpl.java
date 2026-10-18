package org.unischeduler.academic_catalog.infrastructure.adapter.repository;

import org.unischeduler.academic_catalog.domain.entity.AcademicProgram;
import org.unischeduler.academic_catalog.domain.outputports.AcademicProgramRepository;
import org.unischeduler.academic_catalog.infrastructure.entity.AcademicProgramEntity;
import org.unischeduler.academic_catalog.infrastructure.mapper.AcademicProgramMapper;
import org.unischeduler.academic_catalog.infrastructure.repository.postgres.SpringAcademicProgramRepository;

import java.util.List;
import java.util.Optional;

public class AcademicProgramRepositoryImpl implements AcademicProgramRepository {
    private final SpringAcademicProgramRepository programRepository;

    public AcademicProgramRepositoryImpl(SpringAcademicProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    @Override
    public Optional<AcademicProgram> findById(Long id) {
        Optional<AcademicProgramEntity> entityOptional = programRepository.findById(id);
        if(entityOptional.isEmpty()) {
            return Optional.empty();
        }

        AcademicProgramEntity entity = entityOptional.get();
        return Optional.of(AcademicProgramMapper.toDomain(entity));
    }

    @Override
    public List<AcademicProgram> findAll() {
        List<AcademicProgramEntity> entities = programRepository.findAll();

        return entities.stream()
                .map(AcademicProgramMapper::toDomain)
                .toList();
    }
}
