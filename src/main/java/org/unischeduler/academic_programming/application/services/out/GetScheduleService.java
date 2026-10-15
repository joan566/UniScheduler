package org.unischeduler.academic_programming.application.services.out;

import org.unischeduler.academic_programming.application.services.out.dtos.GetScheduleResponse;
import org.unischeduler.academic_programming.application.services.out.dtos.ScheduleInfo;
import org.unischeduler.shared.domain.exceptions.EntityNotFoundException;
import org.unischeduler.academic_programming.domain.entity.GroupSchedule;
import org.unischeduler.enrollment.domain.entity.Enrollment;
import org.unischeduler.academic_programming.domain.inputports.schedule.GetScheduleUseCase;
import org.unischeduler.academic_programming.domain.outputports.GroupScheduleRepository;
import org.unischeduler.enrollment.domain.outputports.EnrollmentRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GetScheduleService implements GetScheduleUseCase {
    private final EnrollmentRepository enrollmentRepository;
    private final GroupScheduleRepository groupScheduleRepository;

    public GetScheduleService(EnrollmentRepository enrollmentRepository, GroupScheduleRepository groupScheduleRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.groupScheduleRepository = groupScheduleRepository;
    }

    @Override
    public GetScheduleResponse execute(GetScheduleCommand command) {

        Enrollment enrollment =
                enrollmentRepository.findByStudentAndActivePeriod(command.getStudentId())
                        .orElseThrow(() -> new EntityNotFoundException(
                                "El estudiante no tiene una matricula en este periodo academico"
                        ));

        Map<String, String> courseNameByGroupId = enrollment.getDetails().stream()
                .collect(Collectors.toMap(
                        d -> d.getGroup().getGroupId(),
                        d -> d.getGroup().getCourse().getName()
                ));

        List<GroupSchedule> schedules = enrollment.getDetails().stream()
                .map(d -> groupScheduleRepository.findAllWhereGroupId(
                        d.getGroup().getGroupId()
                ))
                .flatMap(List::stream)
                .toList();

        List<ScheduleInfo> scheduleInfos = schedules.stream()
                .map(s -> ScheduleInfo.toScheduleInfo(
                        s,
                        courseNameByGroupId.get(s.getGroupId())
                ))
                .toList();

        return new GetScheduleResponse(
                true,
                "Horario retornado con exito",
                scheduleInfos
        );
    }
}
