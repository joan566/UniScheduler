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

        // TODO: Replace the hardcoded user ID with the authenticate user's ID extracted from JWT once the token flow is implemented.
        Optional<User> optionalUser = userRepository.findById(1L);

        if (optionalUser.isEmpty()) {
            return new ChangePasswordResponse(
                    false,
                    "El usuario no existe."
            );
        }

        User user = optionalUser.get();

        if (!passwordEncoderPort.matches(
                command.currentPassword(),
                user.getPassword().getValue())) {

            return new ChangePasswordResponse(
                    false,
                    "La contraseña actual es incorrecta."
            );
        }

        if (!command.newPassword()
                .equals(command.confirmPassword())) {

            return new ChangePasswordResponse(
                    false,
                    "La confirmación de la contraseña no coincide."
            );
        }

        if (passwordEncoderPort.matches(
                command.newPassword(),
                user.getPassword().getValue())) {

            return new ChangePasswordResponse(
                    false,
                    "La nueva contraseña debe ser diferente a la actual."
            );
        }

        String encodedPassword =
                passwordEncoderPort.encode(command.newPassword());

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