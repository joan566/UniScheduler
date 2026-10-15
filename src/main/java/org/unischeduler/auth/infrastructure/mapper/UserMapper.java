package org.unischeduler.auth.infrastructure.mapper;

import org.unischeduler.auth.domain.entity.User;
import org.unischeduler.auth.domain.enums.DocumentType;
import org.unischeduler.auth.domain.enums.Gender;
import org.unischeduler.auth.domain.enums.Role;
import org.unischeduler.auth.domain.enums.Status;
import org.unischeduler.auth.domain.valueobjects.Email;
import org.unischeduler.auth.domain.valueobjects.EncodedPassword;
import org.unischeduler.auth.infrastructure.entity.UserEntity;

public class UserMapper {

    public static UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();

        entity.setUserId(user.getUserId());

        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());

        entity.setDocumentType(user.getDocumentType().name());
        entity.setDocumentNumber(user.getDocumentNumber());

        entity.setBirthDate(user.getBirthDate());
        entity.setGender(user.getGender().name());

        entity.setPhoneNumber(user.getPhoneNumber());
        entity.setAddress(user.getAddress());

        entity.setEmail(user.getEmail().getValue());
        entity.setPassword(user.getPassword().getValue());

        entity.setStatus(user.getStatus().name());
        entity.setRole(user.getRole().name());

        entity.setCreatedAt(user.getCreatedAt());

        return entity;
    }

    public static User toDomain(UserEntity entity) {

        return new User(
                entity.getUserId(),

                entity.getFirstName(),
                entity.getLastName(),

                DocumentType.valueOf(entity.getDocumentType()),
                entity.getDocumentNumber(),

                entity.getBirthDate(),

                Gender.valueOf(entity.getGender()),

                entity.getPhoneNumber(),
                entity.getAddress(),

                new Email(entity.getEmail()),
                new EncodedPassword(entity.getPassword()),

                Status.valueOf(entity.getStatus()),
                Role.valueOf(entity.getRole()),

                entity.getCreatedAt()
        );
    }
}