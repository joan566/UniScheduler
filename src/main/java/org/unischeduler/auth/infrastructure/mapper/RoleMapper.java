package org.unischeduler.auth.infrastructure.mapper;

public class RoleMapper {
    public static Long toId(String role) {
        return switch (role.toUpperCase()) {
            case "ADMIN" -> 1L;
            case "TEACHER" -> 2L;
            case "STUDENT" -> 3L;
            default -> throw new IllegalArgumentException("Invalid role");
        };
    }
}