package org.unischeduler.academic_catalog.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "courses",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_courses_code", columnNames = "code"
        )
)
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "code", nullable = false, length = 30, updatable = false)
    private String code;

    @Column(name = "credits", nullable = false)
    private int credits;

    @Column(name = "description", columnDefinition = "text", updatable = false)
    private String description;

}
