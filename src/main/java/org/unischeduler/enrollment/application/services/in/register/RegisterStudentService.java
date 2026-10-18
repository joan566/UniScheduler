package org.unischeduler.enrollment.application.services.in.register;

import org.unischeduler.auth.application.services.in.login.RegisterUserCommand;
import org.unischeduler.auth.application.services.in.login.dtos.RegisterUserResponse;
import org.unischeduler.enrollment.application.services.in.register.dtos.RegisterStudentResponse;
import org.unischeduler.enrollment.application.services.in.register.dtos.StudentInfo;
import org.unischeduler.shared.domain.exceptions.EntityNotFoundException;
import org.unischeduler.academic_catalog.domain.entity.AcademicProgram;
import org.unischeduler.auth.domain.entity.User;
import org.unischeduler.enrollment.domain.entity.Student;
import org.unischeduler.auth.domain.inputports.RegisterUserUseCase;
import org.unischeduler.enrollment.domain.inputports.RegisterStudentUseCase;
import org.unischeduler.auth.domain.outputports.PasswordGeneratorPort;
import org.unischeduler.enrollment.domain.outputports.StudentCodeGeneratorPort;
import org.unischeduler.academic_catalog.domain.outputports.AcademicProgramRepository;
import org.unischeduler.auth.domain.outputports.UserRepository;
import org.unischeduler.enrollment.domain.outputports.StudentRepository;
import java.util.Optional;

public class RegisterStudentService implements RegisterStudentUseCase {
    private final RegisterUserUseCase registerUserUseCase;
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final AcademicProgramRepository academicProgramRepository;
    private final PasswordGeneratorPort passwordGenerator;
    private final StudentCodeGeneratorPort studentCodeGenerator;

    public RegisterStudentService(RegisterUserUseCase registerUserUseCase, UserRepository userRepository, StudentRepository studentRepository, AcademicProgramRepository academicProgramRepository, PasswordGeneratorPort passwordGenerator, StudentCodeGeneratorPort studentCodeGenerator) {
        this.registerUserUseCase = registerUserUseCase;
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.academicProgramRepository = academicProgramRepository;
        this.passwordGenerator = passwordGenerator;
        this.studentCodeGenerator = studentCodeGenerator;
    }

    @Override
    public RegisterStudentResponse execute(RegisterStudentCommand command) {

        // Random Password Generation
        String rawPassword = passwordGenerator.generatePassword();
        RegisterUserCommand registerUserCommand = new RegisterUserCommand(
                command.getFirstName(),
                command.getLastName(),
                command.getDocumentType(),
                command.getDocumentNumber(),
                command.getBirthDate(),
                command.getGender(),
                command.getPhoneNumber(),
                command.getAddress(),
                command.getEmail(),
                rawPassword
        );

        RegisterUserResponse userResponse = registerUserUseCase.execute(registerUserCommand);

        if(!userResponse.isSuccessfully()) {
            return new RegisterStudentResponse(
                    false,
                    userResponse.getMessage(),
                    null
            );
        }

        Optional<User> userSavedOptional = userRepository.findById(
                userResponse.getUser().getUserId()
        );

        if(userSavedOptional.isEmpty()) {
            return new RegisterStudentResponse(
                    false,
                    "No se encontro el usuario",
                    null
            );
        }

        User userSaved = userSavedOptional.get();


        AcademicProgram academicProgram = academicProgramRepository.findById(command.getAcademicProgramId())
                .orElseThrow(() -> new EntityNotFoundException("No se encontro un periodo acadmico con id: " + command.getAcademicProgramId()));

        String studentCode;
        int attempts = 0;
        int maxAttempts = 5;

        do {
            // Random StudentCode Generation
            studentCode = studentCodeGenerator.execute();
            attempts++;

            if(attempts > maxAttempts) {
                throw new IllegalStateException("No se pudo generar un código único para el estudiante");
            }

        } while(studentRepository.existsByStudentCode(studentCode));

        Student student = new Student(
                null,
                studentCode,
                userSaved,
                academicProgram
        );

        Student studentSaved = studentRepository.save(student);

        return new RegisterStudentResponse(
                true,
                "El estudiante fue registrado con exito",
                new StudentInfo(
                        userSaved.getId(),
                        studentSaved.getId(),
                        studentSaved.getStudentCode(),
                        rawPassword
                )
        );
    }
}
