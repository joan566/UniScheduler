package org.unischeduler.academic_history.infrastructure.adapter.repository;

import org.unischeduler.shared.domain.exceptions.EntityNotFoundException;
import org.unischeduler.academic_catalog.domain.entity.Course;
import org.unischeduler.academic_history.domain.entity.AcademicHistory;
import org.unischeduler.enrollment.domain.entity.Student;
import org.unischeduler.academic_catalog.domain.outputports.CourseRepository;
import org.unischeduler.academic_history.domain.outputports.AcademicHistoryRepository;
import org.unischeduler.enrollment.domain.outputports.StudentRepository;
import org.unischeduler.academic_history.infrastructure.entity.AcademicHistoryEntity;
import org.unischeduler.academic_history.infrastructure.mapper.AcademicHistoryMapper;
import org.unischeduler.academic_history.infrastructure.repository.excel.ExcelAcademicHistoryRepository;

import java.util.List;
import java.util.Optional;

public class AcademicHistoryRepositoryImpl implements AcademicHistoryRepository {
    private final ExcelAcademicHistoryRepository academicHistoryRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public AcademicHistoryRepositoryImpl(ExcelAcademicHistoryRepository academicHistoryRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.academicHistoryRepository = academicHistoryRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Optional<AcademicHistory> findByStudentId(String studentId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if(studentOptional.isEmpty()) {
            return Optional.empty();
        }

        Student student = studentOptional.get();

        Optional<AcademicHistoryEntity> entityOptional =
                academicHistoryRepository.findByStudentId(studentId);

        if (entityOptional.isEmpty()) {
            return Optional.empty();
        }

        AcademicHistoryEntity entity = entityOptional.get();

        List<Course> courses =
                entity.getCompletedCoursesId()
                        .stream()
                        .map(courseRepository::findById)
                        .flatMap(Optional::stream)
                        .toList();

        AcademicHistory history =
                AcademicHistoryMapper.toDomain(
                        entity,
                        student,
                        courses
                );

        return Optional.of(history);
    }

    @Override
    public AcademicHistory save(AcademicHistory academicHistory) {
        AcademicHistoryEntity entity = academicHistoryRepository.save(
                AcademicHistoryMapper.toEntity(academicHistory)
        );

        List<Course> courses =
                entity.getCompletedCoursesId()
                        .stream()
                        .map(courseRepository::findById)
                        .flatMap(Optional::stream)
                        .toList();

        Student student = studentRepository.findById(entity.getStudentId())
                .orElseThrow(()
                -> new EntityNotFoundException("No se encontro un estudiante con id " + entity.getStudentId()));

        return AcademicHistoryMapper.toDomain(entity, student, courses);
    }
}
