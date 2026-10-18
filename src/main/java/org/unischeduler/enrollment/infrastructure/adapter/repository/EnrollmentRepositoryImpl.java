package org.unischeduler.enrollment.infrastructure.adapter.repository;

import org.springframework.stereotype.Repository;
import org.unischeduler.academic_catalog.domain.exceptions.PeriodActiveNotFoundException;
import org.unischeduler.shared.domain.exceptions.EntityNotFoundException;
import org.unischeduler.academic_catalog.domain.entity.AcademicPeriod;
import org.unischeduler.academic_catalog.domain.entity.AcademicProgram;
import org.unischeduler.enrollment.domain.entity.Enrollment;
import org.unischeduler.enrollment.domain.entity.EnrollmentDetail;
import org.unischeduler.enrollment.domain.entity.Student;
import org.unischeduler.academic_catalog.domain.outputports.AcademicPeriodRepository;
import org.unischeduler.academic_catalog.domain.outputports.AcademicProgramRepository;
import org.unischeduler.enrollment.domain.outputports.EnrollmentDetailRepository;
import org.unischeduler.enrollment.domain.outputports.EnrollmentRepository;
import org.unischeduler.enrollment.domain.outputports.StudentRepository;
import org.unischeduler.enrollment.infrastructure.entity.EnrollmentEntity;
import org.unischeduler.enrollment.infrastructure.mapper.EnrollmentMapper;
import org.unischeduler.enrollment.infrastructure.repository.postgres.SpringEnrollmentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EnrollmentRepositoryImpl implements EnrollmentRepository {
    private final SpringEnrollmentRepository enrollmentRepository;
    private final AcademicProgramRepository programRepository;
    private final StudentRepository studentRepository;
    private final EnrollmentDetailRepository enrollmentDetailRepository;
    private final AcademicPeriodRepository academicPeriodRepository;

    public EnrollmentRepositoryImpl(SpringEnrollmentRepository enrollmentRepository, AcademicProgramRepository programRepository, StudentRepository studentRepository, EnrollmentDetailRepository enrollmentDetailRepository, AcademicPeriodRepository academicPeriodRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.programRepository = programRepository;
        this.studentRepository = studentRepository;
        this.enrollmentDetailRepository = enrollmentDetailRepository;
        this.academicPeriodRepository = academicPeriodRepository;
    }

    @Override
    public Enrollment save(Enrollment enrollment) {
        EnrollmentEntity entity = EnrollmentMapper.toEntity(enrollment);

        EnrollmentEntity entitySaved = enrollmentRepository.save(entity);
        AcademicProgram program = programRepository.findById(entitySaved.getAcademicProgramId())
                .orElseThrow(() -> new EntityNotFoundException("No existe un programa academico con el id: " + entitySaved.getAcademicProgramId()));
        Student student = studentRepository.findById(entitySaved.getStudentId())
                .orElseThrow(() -> new EntityNotFoundException("No existe un estudiante con el id: " + entitySaved.getStudentId()));

        AcademicPeriod academicPeriod = academicPeriodRepository.findById(entitySaved.getAcademicPeriodId())
                .orElseThrow(() -> new EntityNotFoundException("No se encontro un periodo academico con id " + entitySaved.getAcademicPeriodId()));

        return EnrollmentMapper.toDomain(entitySaved, student, program, null, academicPeriod);
    }

    public Optional<Enrollment> findByStudentAndActivePeriod(Long studentId) {
        AcademicPeriod academicPeriod = academicPeriodRepository.findActive()
                .orElseThrow(() -> new PeriodActiveNotFoundException(
                        "No se encontro un periodo academico activo"));

        Optional<EnrollmentEntity> entityOpt = enrollmentRepository.findByStudentIdAndAcademicPeriodId(
                studentId,
                academicPeriod.getAcademicPeriodId()
        );

        if (entityOpt.isEmpty()) {
            return Optional.empty();
        }

        EnrollmentEntity entity = entityOpt.get();

        AcademicProgram program = programRepository.findById(entity.getAcademicProgramId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "No existe un programa academico con el id: "
                                + entity.getAcademicProgramId()));

        Student student = studentRepository.findById(entity.getStudentId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "No existe un estudiante con el id: "
                                + entity.getStudentId()));

        ArrayList<EnrollmentDetail> details = enrollmentDetailRepository
                .findByEnrollmentId(entity.getEnrollmentId());

        return Optional.of(
                EnrollmentMapper.toDomain(
                        entity,
                        student,
                        program,
                        details,
                        academicPeriod
                )
        );
    }

    @Override
    public List<Enrollment> findAllWhereStudentId(Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No existe un estudiante con el id: " + studentId));

        List<EnrollmentEntity> entities =
                enrollmentRepository.findAllByStudentId(studentId);

        return entities.stream()
                .map(entity -> {

                    AcademicProgram program = programRepository.findById(entity.getAcademicProgramId())
                            .orElseThrow(() -> new EntityNotFoundException(
                                    "No existe un programa académico con el id: "
                                            + entity.getAcademicProgramId()));

                    AcademicPeriod period = academicPeriodRepository.findById(entity.getAcademicPeriodId())
                            .orElseThrow(() -> new EntityNotFoundException(
                                    "No existe un período académico con el id: "
                                            + entity.getAcademicPeriodId()));

                    ArrayList<EnrollmentDetail> details =
                            enrollmentDetailRepository.findByEnrollmentId(
                                    entity.getEnrollmentId());

                    return EnrollmentMapper.toDomain(
                            entity,
                            student,
                            program,
                            details,
                            period
                    );
                })
                .toList();
    }
}
