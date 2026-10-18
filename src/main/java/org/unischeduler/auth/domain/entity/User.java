package org.unischeduler.auth.domain.entity;

import org.unischeduler.auth.domain.enums.DocumentType;
import org.unischeduler.auth.domain.enums.Gender;
import org.unischeduler.auth.domain.enums.Role;
import org.unischeduler.auth.domain.enums.Status;
import org.unischeduler.auth.domain.valueobjects.Email;
import org.unischeduler.auth.domain.valueobjects.EncodedPassword;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class User {
    // Identificación
    private final Long id;

    // Información personal
    private final String firstName;
    private final String lastName;
    private DocumentType documentType;
    private final String documentNumber;
    private final LocalDate birthDate;
    private final Gender gender;

    // Contacto
    private String phoneNumber;
    private String address;
    private final Email email;

    // Seguridad
    private EncodedPassword password;

    // Sistema
    private Status status;
    private final LocalDateTime createdAt;

    public User(Long id, String firstName, String lastName,
                DocumentType documentType, String documentNumber, LocalDate birthDate,
                Gender gender, String phoneNumber, String address,
                Email email, EncodedPassword password, Status status, LocalDateTime createdAt) {


        this.id = id;
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
        this.status = status;
        this.createdAt = createdAt != null? createdAt : LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public Email getEmail() {
        return email;
    }

    public EncodedPassword getPassword() {
        return password;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // Setters

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPassword(EncodedPassword password) {
        this.password = password;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
