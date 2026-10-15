package org.unischeduler.academic_programming.domain.outputports;

import org.unischeduler.academic_programming.domain.entity.SemesterTemplateDetail;

import java.util.ArrayList;

public interface SemesterTemplateDetailRepository {
    ArrayList<SemesterTemplateDetail> findAllWhereTemplateId(String templateId);
}
