package org.unischeduler.academic_programming.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name = "groups",
        uniqueConstraints = @UniqueConstraint(
                name = "uq_groups_course_period_code",
                columnNames = {"course_id", "academic_period_id", "group_code"}
        )
)
public class GroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "course_id", nullable = false)
    private Long courseId;

    @Column(name = "group_code", nullable = false, length = 30)
    private String groupCode;

    @Column(name = "teacher_id", nullable = false)
    private Long teacherId;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    @Column(name = "academic_period_id", nullable = false)
    private Long academicPeriodId;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
