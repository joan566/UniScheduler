package org.unischeduler.academic_programming.infrastructure.adapter.repository;

import org.springframework.stereotype.Repository;
import org.unischeduler.shared.domain.exceptions.EntityNotFoundException;
import org.unischeduler.academic_catalog.domain.entity.AcademicProgram;
import org.unischeduler.academic_programming.domain.entity.SemesterTemplate;
import org.unischeduler.academic_programming.domain.entity.SemesterTemplateDetail;
import org.unischeduler.academic_programming.domain.outputports.SemesterTemplateDetailRepository;
import org.unischeduler.academic_programming.domain.outputports.SemesterTemplateRepository;
import org.unischeduler.academic_programming.infrastructure.entity.SemesterTemplateEntity;
import org.unischeduler.academic_programming.infrastructure.mapper.SemesterTemplateMapper;
import org.unischeduler.academic_programming.infrastructure.repository.postgres.SpringSemesterTemplateRepository;

import java.util.List;

@Repository
public class SemesterTemplateRepositoryImpl implements SemesterTemplateRepository {
    private final SpringSemesterTemplateRepository semesterTemplateRepository;
    private final SemesterTemplateDetailRepository semesterTemplateDetailRepository;

    public SemesterTemplateRepositoryImpl(SpringSemesterTemplateRepository semesterTemplateRepository, SemesterTemplateDetailRepository semesterTemplateDetailRepository) {
        this.semesterTemplateRepository = semesterTemplateRepository;
        this.semesterTemplateDetailRepository = semesterTemplateDetailRepository;
    }

    @Override
    public SemesterTemplate findByProgramAndSemester(AcademicProgram program, int semester) {
        SemesterTemplateEntity entity = semesterTemplateRepository
                .findByProgramIdAndSemester(program.getId(), semester)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No existe una plantilla para el programa "
                                + program.getId()
                                + " y semestre "
                                + semester
                ));

        List<SemesterTemplateDetail> templateDetails = semesterTemplateDetailRepository.findAllWhereTemplateId(entity.getTemplateId());
        return SemesterTemplateMapper.toDomain(
                entity,
                program,
                templateDetails
        );
    }
}
