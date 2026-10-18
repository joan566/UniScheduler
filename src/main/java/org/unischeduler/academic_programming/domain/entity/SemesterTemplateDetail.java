package org.unischeduler.academic_programming.domain.entity;

public class SemesterTemplateDetail {
    private Long id;
    private Group group;

    public SemesterTemplateDetail(Long id, Group group) {
        this.id = id;
        this.group = group;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
