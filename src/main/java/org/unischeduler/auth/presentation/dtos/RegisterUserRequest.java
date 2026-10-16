package org.unischeduler.auth.presentation.dtos;

import java.time.LocalDate;

public class RegisterUserRequest {
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

    public RegisterUserRequest(String firstName, String lastName, String documentType, String documentNumber,
                               LocalDate birthDate, String gender, String phoneNumber, String address,
                               String email, String password) {
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
}
