package org.unischeduler.backend.application.service.auth.login;

import org.unischeduler.backend.application.service.auth.login.dtos.RegisterUserInfo;
import org.unischeduler.backend.application.service.auth.login.dtos.RegisterUserResponse;
import org.unischeduler.backend.application.service.auth.login.dtos.UserInfo;
import org.unischeduler.backend.domain.exceptions.auth.DocumentAlreadyExistsException;
import org.unischeduler.backend.domain.exceptions.auth.EmailAlreadyExistsException;
import org.unischeduler.backend.domain.exceptions.shared.EntityNotFoundException;
import org.unischeduler.backend.domain.model.academic_catalog.entity.AcademicProgram;
import org.unischeduler.backend.domain.model.auth.entity.User;
import org.unischeduler.backend.domain.model.auth.enums.DocumentType;
import org.unischeduler.backend.domain.model.auth.enums.Gender;
import org.unischeduler.backend.domain.model.auth.enums.Role;
import org.unischeduler.backend.domain.model.auth.enums.Status;
import org.unischeduler.backend.domain.model.auth.vo.Email;
import org.unischeduler.backend.domain.model.auth.vo.EncodedPassword;
import org.unischeduler.backend.domain.port.in.auth.RegisterUserUseCase;
import org.unischeduler.backend.domain.port.out.academic_catalog.AcademicProgramRepository;
import org.unischeduler.backend.domain.port.out.auth.UserRepository;
import org.unischeduler.backend.domain.port.out.security.PasswordEncoderPort;

import java.util.Optional;

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
                command.getRole() == null ?
                        null : Role.valueOf(command.getRole()),
                null
        );

        User userSaved = userRepository.save(user);

        return new RegisterUserResponse(
                true,
                "Se Registro un usuario con exito",
                new RegisterUserInfo(
                        userSaved.getUserId(),
                        userSaved.getFirstName() + " " + userSaved.getLastName(),
                        userSaved.getEmail().getValue(),
                        userSaved.getRole().name()
                )
        );
    }
}
