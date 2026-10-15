package org.unischeduler.backend.infrastructure.repository.academic_programming;

import org.unischeduler.academic_catalog.domain.entity.AcademicProgram;
import org.unischeduler.backend.domain.model.academic_programming.entity.SemesterTemplate;
import org.unischeduler.backend.domain.model.academic_programming.entity.SemesterTemplateDetail;
import org.unischeduler.backend.domain.port.out.academic_programming.SemesterTemplateDetailRepository;
import org.unischeduler.backend.domain.port.out.academic_programming.SemesterTemplateRepository;
import org.unischeduler.academic_catalog.infrastructure.entity.AcademicProgramEntity;
import org.unischeduler.backend.infrastructure.entity.academic_programming.SemesterTemplateEntity;
import org.unischeduler.academic_catalog.infrastructure.mapper.AcademicProgramMapper;
import org.unischeduler.backend.infrastructure.mapper.academic_programming.SemesterTemplateMapper;
import org.unischeduler.backend.infrastructure.persistence.excel.repository.academic_program.ExcelSemesterTemplateRepository;

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
