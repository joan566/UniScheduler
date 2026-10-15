package org.unischeduler.academic_programming.infrastructure.adapter.repository;

import org.unischeduler.academic_catalog.domain.entity.AcademicProgram;
import org.unischeduler.academic_programming.domain.entity.SemesterTemplate;
import org.unischeduler.academic_programming.domain.entity.SemesterTemplateDetail;
import org.unischeduler.academic_programming.domain.outputports.SemesterTemplateDetailRepository;
import org.unischeduler.academic_programming.domain.outputports.SemesterTemplateRepository;
import org.unischeduler.academic_catalog.infrastructure.entity.AcademicProgramEntity;
import org.unischeduler.academic_programming.infrastructure.entity.SemesterTemplateEntity;
import org.unischeduler.academic_catalog.infrastructure.mapper.AcademicProgramMapper;
import org.unischeduler.academic_programming.infrastructure.mapper.SemesterTemplateMapper;
import org.unischeduler.academic_programming.infrastructure.repository.excel.ExcelSemesterTemplateRepository;

import java.util.List;

public class SemesterTemplateRepositoryImpl implements SemesterTemplateRepository {
    private final ExcelSemesterTemplateRepository semesterTemplateRepository;
    private final SemesterTemplateDetailRepository semesterTemplateDetailRepository;

    public SemesterTemplateRepositoryImpl(ExcelSemesterTemplateRepository semesterTemplateRepository, SemesterTemplateDetailRepository semesterTemplateDetailRepository) {
        this.semesterTemplateRepository = semesterTemplateRepository;
        this.semesterTemplateDetailRepository = semesterTemplateDetailRepository;
    }

    @Override
    public SemesterTemplate findByProgramAndSemester(AcademicProgram program, int semester) {
        AcademicProgramEntity academicProgramEntity = AcademicProgramMapper.toEntity(program);
        SemesterTemplateEntity entity = semesterTemplateRepository.findByProgramAndSemester(academicProgramEntity, semester);

        List<SemesterTemplateDetail> templateDetails = semesterTemplateDetailRepository.findAllWhereTemplateId(entity.getTemplateId());
        return SemesterTemplateMapper.toDomain(
                entity,
                program,
                templateDetails
        );
    }
}
