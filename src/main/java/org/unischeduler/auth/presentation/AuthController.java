package org.unischeduler.auth.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unischeduler.auth.application.services.in.ChangePasswordCommand;
import org.unischeduler.auth.application.services.in.ChangePasswordResponse;
import org.unischeduler.auth.application.services.in.login.LoginUserCommand;
import org.unischeduler.auth.application.services.in.login.RegisterUserCommand;
import org.unischeduler.auth.application.services.in.login.dtos.LoginUserResponse;
import org.unischeduler.auth.application.services.in.login.dtos.RegisterUserResponse;
import org.unischeduler.auth.domain.inputports.ChangePasswordUseCase;
import org.unischeduler.auth.domain.inputports.LoginUserUseCase;
import org.unischeduler.auth.domain.inputports.RegisterUserUseCase;
import org.unischeduler.auth.presentation.dtos.ChangePasswordRequest;
import org.unischeduler.auth.presentation.dtos.LoginUserRequest;
import org.unischeduler.auth.presentation.dtos.RegisterUserRequest;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    private final RegisterUserUseCase registerUserUseCase;
    private final ChangePasswordUseCase changePasswordUseCase;
    private final LoginUserUseCase loginUserUseCase;

    public AuthController(RegisterUserUseCase registerUserUseCase, ChangePasswordUseCase changePasswordUseCase, LoginUserUseCase loginUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
        this.changePasswordUseCase = changePasswordUseCase;
        this.loginUserUseCase = loginUserUseCase;
    }

    @PostMapping("/users")
    public ResponseEntity<RegisterUserResponse> registerUser(@RequestBody RegisterUserRequest request) {
        RegisterUserCommand command = new RegisterUserCommand(
                request.getFirstName(),
                request.getLastName(),
                request.getDocumentType(),
                request.getDocumentNumber(),
                request.getBirthDate(),
                request.getGender(),
                request.getPhoneNumber(),
                request.getAddress(),
                request.getEmail(),
                request.getPassword()
        );

        return ResponseEntity.ok(registerUserUseCase.execute(command));
    }

    @PostMapping("login")
    public ResponseEntity<LoginUserResponse> loginUser(@RequestBody LoginUserRequest request) {
        LoginUserCommand command = new LoginUserCommand(
                request.email(),
                request.password()
        );

        return ResponseEntity.ok(loginUserUseCase.execute(command));
    }

    @PostMapping("change-password")
    public ResponseEntity<ChangePasswordResponse> changePassword(@RequestBody ChangePasswordRequest request) {
        ChangePasswordCommand command = new ChangePasswordCommand(
                request.currentPassword(),
                request.newPassword(),
                request.confirmPassword()
        );

        return ResponseEntity.ok(changePasswordUseCase.execute(command));
    }
}
