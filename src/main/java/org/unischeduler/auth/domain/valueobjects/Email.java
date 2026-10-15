package org.unischeduler.auth.domain.valueobjects;

public class Email {
    private final String value;

    public Email(String value) {
        if (value == null || !value.contains("@")) {
            throw new IllegalArgumentException("Email inválido");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
