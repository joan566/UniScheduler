package org.unischeduler.enrollment.infrastructure.entity;

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

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name = "students",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_students_student_code", columnNames = "student_code"),
                @UniqueConstraint(name = "uk_students_user_id", columnNames = "user_id")
        }
)
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_code", nullable = false, length = 30)
    private String studentCode;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "academic_program_id", nullable = false)
    private Long academicProgramId;
}
