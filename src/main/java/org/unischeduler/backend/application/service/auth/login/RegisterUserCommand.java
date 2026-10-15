package org.unischeduler.backend.application.service.auth.login;

import java.time.LocalDate;

public class RegisterUserCommand {
    private final String firstName;
    private final String lastName;
    private final String documentType;
    private final String documentNumber;
    private final LocalDate birthDate;
    private final String gender;

    // Contacto
    private final String phoneNumber;
    private final String address;
    private final String email;
    private final String password;

    private final String role;
    private final String status;

    public RegisterUserCommand(String firstName, String lastName, String documentType,
                                  String documentNumber, LocalDate birthDate, String gender,
                                  String phoneNumber, String address, String email, String password,
                                  String role, String status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDocumentType() {
        return documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getGender() {
        return gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return this.role;
    }

    public String getStatus() {
        return status;
    }
}
