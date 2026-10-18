package org.unischeduler.academic_programming.infrastructure.adapter.repository;

import org.springframework.stereotype.Repository;
import org.unischeduler.shared.domain.exceptions.EntityNotFoundException;
import org.unischeduler.academic_programming.domain.entity.Group;
import org.unischeduler.academic_programming.domain.entity.SemesterTemplateDetail;
import org.unischeduler.academic_programming.domain.outputports.GroupRepository;
import org.unischeduler.academic_programming.domain.outputports.SemesterTemplateDetailRepository;
import org.unischeduler.academic_programming.infrastructure.entity.SemesterTemplateDetailEntity;
import org.unischeduler.academic_programming.infrastructure.mapper.SemesterTemplateDetailMapper;
import org.unischeduler.academic_programming.infrastructure.repository.postgres.SpringSemesterTemplateDetailRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SemesterTemplateDetailRepositoryImpl implements SemesterTemplateDetailRepository {
    private final SpringSemesterTemplateDetailRepository templateDetailRepository;
    private final GroupRepository groupRepository;

    public SemesterTemplateDetailRepositoryImpl(SpringSemesterTemplateDetailRepository templateDetailRepository, GroupRepository groupRepository) {
        this.templateDetailRepository = templateDetailRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    public ArrayList<SemesterTemplateDetail> findAllWhereTemplateId(Long templateId) {
        List<SemesterTemplateDetailEntity> entities = templateDetailRepository.findAllByTemplateId(templateId);

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
