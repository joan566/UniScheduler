package org.unischeduler.auth.infrastructure.mapper;

public class RoleMapper {
    public static String toId(String role) {
        return switch (role.toUpperCase()) {
            case "ADMIN" -> "1";
            case "TEACHER" -> "2";
            case "STUDENT" -> "3";
            default -> throw new IllegalArgumentException("Invalid role");
        };
    }
}