package org.unischeduler.auth.infrastructure.adapter.repository;

import org.unischeduler.auth.domain.entity.User;
import org.unischeduler.auth.domain.outputports.UserRepository;
import org.unischeduler.auth.infrastructure.entity.UserEntity;
import org.unischeduler.auth.infrastructure.mapper.UserMapper;
import org.unischeduler.auth.infrastructure.repository.excel.ExcelUserRepository;

import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private final ExcelUserRepository excelUserRepository;


    public UserRepositoryImpl(ExcelUserRepository excelUserRepository) {
        this.excelUserRepository = excelUserRepository;
    }

    @Override
    public User save(User user) {
        UserEntity entity = UserMapper.toEntity(user);
        return UserMapper.toDomain(excelUserRepository.save(entity));
    }

    @Override
    public User changePassword(User user) {
        UserEntity entity = UserMapper.toEntity(user);

        UserEntity entityUpdated = excelUserRepository.changePassword(entity);

        return UserMapper.toDomain(entityUpdated);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<UserEntity> entityOptional = excelUserRepository.findByEmail(email);

        if(entityOptional.isEmpty()) {
            return Optional.empty();
        }
        UserEntity entity = entityOptional.get();

        return Optional.of(UserMapper.toDomain(entity));
    }

    @Override
    public Optional<User> findById(String id) {
        Optional<UserEntity> entityOptional = excelUserRepository.findById(id);
        if(entityOptional.isEmpty()) {
           return Optional.empty();
        }

        UserEntity entity = entityOptional.get();
        return Optional.of(UserMapper.toDomain(entity));
    }

    @Override
    public boolean existsByEmail(String email) {
        return excelUserRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByDocumentNumber(String documentNumber) {
        return excelUserRepository.existsByDocumentNumber(documentNumber);
    }
}
