package org.unischeduler.backend.presentation;

import org.unischeduler.auth.application.services.in.login.LoginUserCommand;
import org.unischeduler.auth.application.services.in.login.dtos.LoginUserResponse;
import org.unischeduler.enrollment.application.services.in.register.RegisterStudentCommand;
import org.unischeduler.enrollment.application.services.in.register.dtos.RegisterStudentResponse;
import org.unischeduler.auth.domain.inputports.LoginUserUseCase;
import org.unischeduler.enrollment.domain.inputports.RegisterStudentUseCase;
import org.unischeduler.backend.presentation.dtos.LoginUserRequest;
import org.unischeduler.backend.presentation.dtos.RegisterStudentRequest;

public class AuthController {
    private final RegisterStudentUseCase registerStudentUseCase;
    private final LoginUserUseCase loginUserUseCase;

    public AuthController(RegisterStudentUseCase registerStudentUseCase, LoginUserUseCase loginUserUseCase) {
        this.registerStudentUseCase = registerStudentUseCase;
        this.loginUserUseCase = loginUserUseCase;
    }

    public RegisterStudentResponse registerNewStudent(RegisterStudentRequest request) {
        RegisterStudentCommand command = new RegisterStudentCommand(
                request.getFirstName(),
                request.getLastName(),
                request.getDocumentType(),
                request.getDocumentNumber(),
                request.getBirthDate(),
                request.getGender(),
                request.getPhoneNumber(),
                request.getAddress(),
                request.getEmail(),
                request.getAcademicProgramId(),
                request.getInitialSemester(),
                request.getAdmissionDate(),
                request.getStatus()
        );

        return registerStudentUseCase.execute(command);
    }

    public LoginUserResponse loginUser(LoginUserRequest request) {
        LoginUserCommand command = new LoginUserCommand(
                request.getEmail(),
                request.getPassword()
        );

        return loginUserUseCase.execute(command);
    }
}
