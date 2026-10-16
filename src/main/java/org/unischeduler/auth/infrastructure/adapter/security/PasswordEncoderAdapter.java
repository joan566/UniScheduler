package org.unischeduler.auth.infrastructure.adapter.security;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.unischeduler.auth.domain.outputports.PasswordEncoderPort;

@Component
public class PasswordEncoderAdapter implements PasswordEncoderPort {
    @Override
    public String encode(String rawPassword) {
        return BCrypt.hashpw(rawPassword, BCrypt.gensalt());
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        return BCrypt.checkpw(rawPassword, encodedPassword);
    }
}
