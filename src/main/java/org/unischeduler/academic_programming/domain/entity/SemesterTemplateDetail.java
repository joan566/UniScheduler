package org.unischeduler.academic_programming.domain.entity;

public class SemesterTemplateDetail {
    private Long templateDetailId;
    private Group group;

    public SemesterTemplateDetail(Long templateDetailId, Group group) {
        this.templateDetailId = templateDetailId;
        this.group = group;
    }

    public Long getTemplateDetailId() {
        return templateDetailId;
    }

    public void setTemplateDetailId(Long templateDetailId) {
        this.templateDetailId = templateDetailId;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
