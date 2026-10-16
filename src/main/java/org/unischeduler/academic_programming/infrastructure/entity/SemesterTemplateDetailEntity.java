package org.unischeduler.academic_programming.infrastructure.entity;

public class SemesterTemplateDetailEntity {
    private Long templateDetailId;
    private Long templateId;
    private Long groupId;

    public Long getTemplateDetailId() {
        return templateDetailId;
    }

    public void setTemplateDetailId(Long templateDetailId) {
        this.templateDetailId = templateDetailId;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
