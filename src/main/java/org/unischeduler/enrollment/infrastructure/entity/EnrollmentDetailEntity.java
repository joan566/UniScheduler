package org.unischeduler.enrollment.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "enrollment_details")
public class EnrollmentDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enrollment_detail_id")
    private Long enrollmentDetailId;

    @Column(name = "enrollment_id", nullable = false)
    private Long enrollmentId;

    @Column(name = "group_id", nullable = false)
    private Long groupId;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @Column(name = "final_grade")
    private Double finalGrade;
}
