package org.unischeduler.academic_catalog.infrastructure.adapter.repository;


import org.springframework.stereotype.Repository;
import org.unischeduler.academic_catalog.domain.exceptions.PeriodActiveNotFoundException;
import org.unischeduler.academic_catalog.domain.entity.AcademicPeriod;
import org.unischeduler.academic_catalog.infrastructure.entity.AcademicPeriodEntity;
import org.unischeduler.academic_catalog.domain.outputports.AcademicPeriodRepository;

import org.unischeduler.academic_catalog.infrastructure.mapper.AcademicPeriodMapper;
import org.unischeduler.academic_catalog.infrastructure.repository.postgres.SpringAcademicPeriodRepository;

import java.util.List;
import java.util.Optional;


@Repository
public class AcademicPeriodRepositoryImpl implements AcademicPeriodRepository {

    private final SpringAcademicPeriodRepository academicPeriodRepository;

    public AcademicPeriodRepositoryImpl(SpringAcademicPeriodRepository academicPeriodRepository) {
        this.academicPeriodRepository = academicPeriodRepository;
    }

    @Override
    public Optional<AcademicPeriod> findById(Long id) {
        return academicPeriodRepository.findById(id)
                .map(AcademicPeriodMapper::toDomain);
    }

    @Override
    public List<AcademicPeriod> findAll() {
        return academicPeriodRepository.findAll()
                .stream()
                .map(AcademicPeriodMapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsByCode(String code) {
        return academicPeriodRepository.existsByCode(code);
    }

    @Override
    public Optional<AcademicPeriod> findByCode(String code) {
        return academicPeriodRepository.findByCode(code)
                .map(AcademicPeriodMapper::toDomain);
    }

    @Override
    public AcademicPeriod save(AcademicPeriod academicPeriod) {

        AcademicPeriodEntity entity =
                AcademicPeriodMapper.toEntity(academicPeriod);

        AcademicPeriodEntity savedEntity =
                academicPeriodRepository.save(entity);

        return AcademicPeriodMapper.toDomain(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        academicPeriodRepository.deleteById(id);
    }

    @Override
    public Optional<AcademicPeriod> findActive() {
        AcademicPeriodEntity entity = academicPeriodRepository.findActive()
                .orElseThrow(() -> new PeriodActiveNotFoundException("No se encontro ningun periodo academico activo"));

        return Optional.of(AcademicPeriodMapper.toDomain(entity));
    }
}