package org.unischeduler.auth.application.services.in.login;

import org.unischeduler.auth.application.services.in.login.dtos.RegisterUserInfo;
import org.unischeduler.auth.application.services.in.login.dtos.RegisterUserResponse;
import org.unischeduler.auth.domain.exceptions.DocumentAlreadyExistsException;
import org.unischeduler.auth.domain.exceptions.EmailAlreadyExistsException;
import org.unischeduler.auth.domain.entity.User;
import org.unischeduler.auth.domain.enums.DocumentType;
import org.unischeduler.auth.domain.enums.Gender;
import org.unischeduler.auth.domain.enums.Role;
import org.unischeduler.auth.domain.enums.Status;
import org.unischeduler.auth.domain.valueobjects.Email;
import org.unischeduler.auth.domain.valueobjects.EncodedPassword;
import org.unischeduler.auth.domain.inputports.RegisterUserUseCase;
import org.unischeduler.auth.domain.outputports.UserRepository;
import org.unischeduler.auth.domain.outputports.PasswordEncoderPort;

public class RegisterUserService implements RegisterUserUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoderPort passwordEncoderPort;

    public RegisterUserService(UserRepository userRepository, PasswordEncoderPort passwordEncoderPort) {
        this.userRepository = userRepository;
        this.passwordEncoderPort = passwordEncoderPort;
    }

    @Override
    public RegisterUserResponse execute(RegisterUserCommand command) {
        if(userRepository.existsByDocumentNumber(command.getDocumentNumber())) {
            throw new DocumentAlreadyExistsException("El numero de documento ya se encuentra registrado");
        }

        if(userRepository.existsByEmail(command.getEmail())) {
            throw  new EmailAlreadyExistsException("El correo electronico ingresado ya existe");
        }

        String hashedPassword = passwordEncoderPort.encode(command.getPassword());
        EncodedPassword encodedPassword = new EncodedPassword(hashedPassword);

        Email email = new Email(command.getEmail());
        DocumentType documentType = DocumentType.valueOf(command.getDocumentType());
        Gender gender = Gender.valueOf(command.getGender());

        User user = new User(
                null,
                command.getFirstName(),
                command.getLastName(),
                documentType,
                command.getDocumentNumber(),
                command.getBirthDate(),
                gender,
                command.getPhoneNumber(),
                command.getAddress(),
                email,
                encodedPassword,
                Status.valueOf(command.getStatus()),
                null
        );

        User userSaved = userRepository.save(user);

        return new RegisterUserResponse(
                true,
                "Se Registro un usuario con exito",
                new RegisterUserInfo(
                        userSaved.getUserId(),
                        userSaved.getFirstName() + " " + userSaved.getLastName(),
                        userSaved.getEmail().getValue()
                )
        );
    }
}
