package org.unischeduler.auth.application.services.in.login;

import org.unischeduler.auth.application.services.in.login.dtos.LoginUserResponse;
import org.unischeduler.auth.application.services.in.login.dtos.UserInfo;
import org.unischeduler.auth.application.services.in.login.dtos.UserRoleInfo;
import org.unischeduler.auth.domain.exceptions.InvalidCredentialsException;
import org.unischeduler.auth.domain.outputports.AccessTokenIssuerPort;
import org.unischeduler.shared.domain.exceptions.EntityNotFoundException;
import org.unischeduler.auth.domain.entity.User;
import org.unischeduler.enrollment.domain.entity.Student;
import org.unischeduler.auth.domain.inputports.LoginUserUseCase;
import org.unischeduler.auth.domain.outputports.UserRepository;
import org.unischeduler.enrollment.domain.outputports.StudentRepository;
import org.unischeduler.auth.domain.outputports.PasswordEncoderPort;

import java.util.Optional;

public class LoginUserService implements LoginUserUseCase {
    private final UserRepository userRepository;
    private final AccessTokenIssuerPort accessTokenIssuerPort;
    private final PasswordEncoderPort passwordEncoderPort;

    public LoginUserService(UserRepository userRepository, AccessTokenIssuerPort accessTokenIssuerPort, PasswordEncoderPort passwordEncoderPort) {
        this.userRepository = userRepository;
        this.accessTokenIssuerPort = accessTokenIssuerPort;
        this.passwordEncoderPort = passwordEncoderPort;
    }

    @Override
    public LoginUserResponse execute(LoginUserCommand command) {
        User user = userRepository.findByEmail(command.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Usuario o contraseña incorrectos"));


        if(!passwordEncoderPort.matches(command.getPassword(), user.getPassword().getValue())) {
            throw new InvalidCredentialsException("Usuario o contraseña incorrectos");
        }

        String accessToken = accessTokenIssuerPort.issueAccessToken(
                user.getId(),
                user.getEmail().getValue()
        );

        UserInfo userInfo = new UserInfo(
                user.getId(),
                user.getFirstName() + " " + user.getLastName(),
                user.getEmail().getValue()
        );

        return new LoginUserResponse(
                true,
                "Inicio de sesion exitoso",
                userInfo,
                accessToken
        );
    }
}
