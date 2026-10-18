package org.unischeduler.academic_catalog.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "prerequisites")
public class PrerequisiteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prerequisiteId;

    @Column(name = "course_id", nullable = false)
    private Long courseId;

    @Column(name = "prerequisite_course_id", nullable = false)
    private Long prerequisiteCourseId;

}
