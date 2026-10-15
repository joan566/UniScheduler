package org.unischeduler.backend.infrastructure.security;

import org.mindrot.jbcrypt.BCrypt;
import org.unischeduler.auth.domain.outputports.PasswordEncoderPort;

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
