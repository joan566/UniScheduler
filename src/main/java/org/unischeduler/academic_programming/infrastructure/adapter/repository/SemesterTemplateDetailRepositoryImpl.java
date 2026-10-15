package org.unischeduler.academic_programming.infrastructure.adapter.repository;

import org.unischeduler.shared.domain.exceptions.EntityNotFoundException;
import org.unischeduler.academic_programming.domain.entity.Group;
import org.unischeduler.academic_programming.domain.entity.SemesterTemplateDetail;
import org.unischeduler.academic_programming.domain.outputports.GroupRepository;
import org.unischeduler.academic_programming.domain.outputports.SemesterTemplateDetailRepository;
import org.unischeduler.academic_programming.infrastructure.entity.SemesterTemplateDetailEntity;
import org.unischeduler.academic_programming.infrastructure.mapper.SemesterTemplateDetailMapper;
import org.unischeduler.academic_programming.infrastructure.repository.excel.ExcelSemesterTemplateDetailRepository;

import java.util.ArrayList;
import java.util.List;

public class SemesterTemplateDetailRepositoryImpl implements SemesterTemplateDetailRepository {
    private final ExcelSemesterTemplateDetailRepository templateDetailRepository;
    private final GroupRepository groupRepository;

    public SemesterTemplateDetailRepositoryImpl(ExcelSemesterTemplateDetailRepository templateDetailRepository, GroupRepository groupRepository) {
        this.templateDetailRepository = templateDetailRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    public ArrayList<SemesterTemplateDetail> findAllWhereTemplateId(String templateId) {
        List<SemesterTemplateDetailEntity> entities = templateDetailRepository.findAllWhereTemplateId(templateId);

        ArrayList<SemesterTemplateDetail> details = new ArrayList<>();
        for(SemesterTemplateDetailEntity e : entities) {
            Group group = groupRepository.findById(e.getGroupId())
                    .orElseThrow(() -> new EntityNotFoundException("No existe el grupo con id: " + e.getGroupId()));;
            details.add(
                    SemesterTemplateDetailMapper.toDomain(e, group)
            );
        }

        return details;
    }
}
