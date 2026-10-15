package org.unischeduler.auth.application.services.in.login;

import org.unischeduler.auth.application.services.in.login.dtos.LoginUserResponse;
import org.unischeduler.auth.application.services.in.login.dtos.UserInfo;
import org.unischeduler.auth.application.services.in.login.dtos.UserRoleInfo;
import org.unischeduler.backend.domain.exceptions.shared.EntityNotFoundException;
import org.unischeduler.auth.domain.entity.User;
import org.unischeduler.backend.domain.model.enrollment.entity.Student;
import org.unischeduler.auth.domain.inputports.LoginUserUseCase;
import org.unischeduler.auth.domain.outputports.UserRepository;
import org.unischeduler.backend.domain.port.out.enrollment.repository.StudentRepository;
import org.unischeduler.auth.domain.outputports.PasswordEncoderPort;

import java.util.Optional;

public class LoginUserService implements LoginUserUseCase {
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final PasswordEncoderPort passwordEncoderPort;

    public LoginUserService(UserRepository userRepository, StudentRepository studentRepository, PasswordEncoderPort passwordEncoderPort) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.passwordEncoderPort = passwordEncoderPort;
    }

    @Override
    public LoginUserResponse execute(LoginUserCommand command) {
        Optional<User> userOptional = userRepository.findByEmail(command.getEmail());

        if(userOptional.isEmpty()) {
            System.out.println("Case 1");
            return new LoginUserResponse(
                    false,
                    "Usuario o contraseña incorrectos",
                    null
            );
        }

        User user = userOptional.get();

        if(!passwordEncoderPort.matches(command.getPassword(), user.getPassword().getValue())) {
            System.out.println("Case 2");
            return new LoginUserResponse(
                    false,
                    "Usuario o contraseña incorrectos",
                    null
            );
        }


        UserRoleInfo userRoleInfo = new UserRoleInfo();
        if("STUDENT".equals(user.getRole())) {
            Student student = studentRepository.findByUserId(user.getUserId())
                    .orElseThrow(()
                            -> new EntityNotFoundException("No existe un estudiante relacionado a el usuario " + user.getUserId()));

            userRoleInfo.setUserRoleId(student.getStudentId());
            userRoleInfo.setUserRoleCode(student.getStudentCode());

        } else {
            userRoleInfo.setUserRoleId(null);
            userRoleInfo.setUserRoleCode(null);
        }

        UserInfo userInfo = new UserInfo(
                user.getUserId(),
                user.getFirstName() + " " + user.getLastName(),
                user.getEmail().getValue(),
                userRoleInfo,
                user.getRole().name()
        );

        return new LoginUserResponse(
                true,
                "Inicio de sesion exitoso",
                userInfo
        );
    }
}
