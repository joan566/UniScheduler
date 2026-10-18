package org.unischeduler.academic_catalog.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.unischeduler.academic_catalog.domain.enums.AcademicPeriodStatus;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "academic_periods")
public class AcademicPeriodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", nullable = false, length = 20, updatable = false)
    private String code;

    @Column(name = "name", nullable = false, length = 100, updatable = false)
    private String name;

    @Column(name = "start_date", nullable = false, updatable = false)
    private String startDate;

    @Column(name = "end_date", nullable = false, updatable = false)
    private String endDate;

    @Enumerated(EnumType.STRING)
    private AcademicPeriodStatus status;

}
