package org.unischeduler.enrollment.infrastructure.adapter.repository;

import org.springframework.stereotype.Repository;
import org.unischeduler.shared.domain.exceptions.EntityNotFoundException;
import org.unischeduler.academic_catalog.domain.entity.AcademicProgram;
import org.unischeduler.auth.domain.entity.User;
import org.unischeduler.enrollment.domain.entity.Student;
import org.unischeduler.academic_catalog.domain.outputports.AcademicProgramRepository;
import org.unischeduler.auth.domain.outputports.UserRepository;
import org.unischeduler.enrollment.domain.outputports.StudentRepository;
import org.unischeduler.enrollment.infrastructure.entity.StudentEntity;
import org.unischeduler.enrollment.infrastructure.mapper.StudentMapper;
import org.unischeduler.enrollment.infrastructure.repository.postgres.SpringStudentRepository;

import java.util.Optional;

@Repository
public class StudentRepositoryImpl implements StudentRepository {
    private final SpringStudentRepository studentRepository;
    private final UserRepository userRepository;
    private final AcademicProgramRepository academicProgramRepository;

    public StudentRepositoryImpl(SpringStudentRepository studentRepository, UserRepository userRepository, AcademicProgramRepository academicProgramRepository) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.academicProgramRepository = academicProgramRepository;
    }

    @Override
    public Student save(Student student) {
        StudentEntity entity = StudentMapper.toEntity(student);
        StudentEntity entitySaved = studentRepository.save(entity);

        User user = userRepository.findById(entitySaved.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("No existe el usuario con id: " + entitySaved.getUserId()));

        AcademicProgram academicProgram = academicProgramRepository.findById(student.getAcademicProgram().getAcademicProgramId())
                .orElseThrow(() -> new EntityNotFoundException("No se encontro el programa con id "
                + student.getAcademicProgram().getAcademicProgramId()));

        return StudentMapper.toDomain(entitySaved, user, academicProgram);
    }

    @Override
    public Optional<Student> findById(Long id) {
        Optional<StudentEntity> entityOptional = studentRepository.findById(id);
        if(entityOptional.isEmpty()) {
            return Optional.empty();
        }

        StudentEntity entity = entityOptional.get();
        User user = userRepository.findById(entity.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("No existe el usuario con id: " + entity.getUserId()));

        AcademicProgram academicProgram = academicProgramRepository.findById(entity.getAcademicProgramId())
                .orElseThrow(() -> new EntityNotFoundException("No se encontro el programa con id "
                        + entity.getAcademicProgramId()));

        return Optional.of(StudentMapper.toDomain(entity, user, academicProgram));
    }

    @Override
    public boolean existsByStudentCode(String code) {
        return studentRepository.existsByStudentCode(code);
    }

    @Override
    public Optional<Student> findByUserId(Long userId) {
        Optional<StudentEntity> entityOptional = studentRepository.findByUserId(userId);
        if(entityOptional.isEmpty()) {
            return Optional.empty();
        }

        StudentEntity entity = entityOptional.get();
        User user = userRepository.findById(entity.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("No existe el usuario con id: " + entity.getUserId()));

        AcademicProgram academicProgram = academicProgramRepository.findById(entity.getAcademicProgramId())
                .orElseThrow(() -> new EntityNotFoundException("No se encontro el programa con id "
                        + entity.getAcademicProgramId()));

        return Optional.of(StudentMapper.toDomain(entity, user, academicProgram));
    }
}
