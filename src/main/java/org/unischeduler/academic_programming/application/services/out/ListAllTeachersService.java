package org.unischeduler.academic_programming.application.services.out;

import org.unischeduler.academic_programming.application.services.out.dtos.TeacherInfo;
import org.unischeduler.academic_programming.domain.inputports.ListAllTeachersUseCase;
import org.unischeduler.academic_programming.domain.outputports.TeacherRepository;

import java.util.List;

public class ListAllTeachersService implements ListAllTeachersUseCase {
    private final TeacherRepository teacherRepository;

    public ListAllTeachersService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }
    @Override
    public List<TeacherInfo> execute() {
        return teacherRepository.findAll()
                .stream()
                .map(teacher -> new TeacherInfo(
                        teacher.getTeacherId(),
                        teacher.getName()
                ))
                .toList();
    }
}
