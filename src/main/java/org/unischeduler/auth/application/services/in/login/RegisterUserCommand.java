package org.unischeduler.auth.application.services.in.login;

import java.time.LocalDate;

/**
 * @param phoneNumber Contacto
 */
public record RegisterUserCommand(
        String firstName, String lastName, String documentType, String documentNumber,
        LocalDate birthDate, String gender, String phoneNumber, String address, String email,
        String password
) { }
