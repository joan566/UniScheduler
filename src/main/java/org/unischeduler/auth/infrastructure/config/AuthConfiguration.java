package org.unischeduler.auth.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.unischeduler.auth.application.services.in.ChangePasswordService;
import org.unischeduler.auth.application.services.in.login.LoginUserService;
import org.unischeduler.auth.application.services.in.login.RegisterUserService;
import org.unischeduler.auth.domain.inputports.ChangePasswordUseCase;
import org.unischeduler.auth.domain.inputports.LoginUserUseCase;
import org.unischeduler.auth.domain.inputports.RegisterUserUseCase;
import org.unischeduler.auth.domain.outputports.AccessTokenIssuerPort;
import org.unischeduler.auth.domain.outputports.PasswordEncoderPort;
import org.unischeduler.auth.domain.outputports.UserRepository;
import org.unischeduler.enrollment.domain.outputports.StudentRepository;

@Configuration
public class AuthConfiguration {
    @Bean
    LoginUserUseCase loginUserUseCase(UserRepository userRepository,
                                      AccessTokenIssuerPort accessTokenIssuerPort, PasswordEncoderPort passwordEncoderPort) {
        return new LoginUserService(
                userRepository,
                accessTokenIssuerPort,
                passwordEncoderPort
        );
    }

    @Bean
    RegisterUserUseCase registerUserUseCase(UserRepository userRepository, PasswordEncoderPort passwordEncoderPort) {
        return new RegisterUserService(
                userRepository,
                passwordEncoderPort
        );
    }

    @Bean
    ChangePasswordUseCase changePasswordUseCase(UserRepository userRepository,
                                                PasswordEncoderPort passwordEncoderPort) {
        return new ChangePasswordService(userRepository, passwordEncoderPort);
    }
}
