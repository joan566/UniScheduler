package org.unischeduler.auth.application.services.in;

import org.unischeduler.auth.domain.entity.User;
import org.unischeduler.auth.domain.valueobjects.EncodedPassword;
import org.unischeduler.auth.domain.inputports.ChangePasswordUseCase;
import org.unischeduler.auth.domain.outputports.UserRepository;
import org.unischeduler.auth.domain.outputports.PasswordEncoderPort;

import java.util.Optional;

public class ChangePasswordService implements ChangePasswordUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoderPort passwordEncoderPort;

    public ChangePasswordService(
            UserRepository userRepository,
            PasswordEncoderPort passwordEncoderPort
    ) {
        this.userRepository = userRepository;
        this.passwordEncoderPort = passwordEncoderPort;
    }

    @Override
    public ChangePasswordResponse execute(ChangePasswordCommand command) {

        Optional<User> optionalUser =
                userRepository.findById(command.getUserId());

        if (optionalUser.isEmpty()) {
            return new ChangePasswordResponse(
                    false,
                    "El usuario no existe."
            );
        }

        User user = optionalUser.get();

        if (!passwordEncoderPort.matches(
                command.getCurrentPassword(),
                user.getPassword().getValue())) {

            return new ChangePasswordResponse(
                    false,
                    "La contraseña actual es incorrecta."
            );
        }

        if (!command.getNewPassword()
                .equals(command.getConfirmPassword())) {

            return new ChangePasswordResponse(
                    false,
                    "La confirmación de la contraseña no coincide."
            );
        }

        if (passwordEncoderPort.matches(
                command.getNewPassword(),
                user.getPassword().getValue())) {

            return new ChangePasswordResponse(
                    false,
                    "La nueva contraseña debe ser diferente a la actual."
            );
        }

        String encodedPassword =
                passwordEncoderPort.encode(command.getNewPassword());

        user.setPassword(
                new EncodedPassword(encodedPassword)
        );

        userRepository.save(user);

        return new ChangePasswordResponse(
                true,
                "Contraseña actualizada correctamente."
        );
    }
}