package org.unischeduler.enrollment.infrastructure.adapter.security;

import org.unischeduler.enrollment.domain.outputports.StudentCodeGeneratorPort;

import java.security.SecureRandom;

public class StudentCodeGeneratorAdapter implements StudentCodeGeneratorPort {

    @Override
    public String execute() {
        SecureRandom random = new SecureRandom();
        int numero = 10000000 + random.nextInt(90000000);
        return String.valueOf(numero);
    }
}
