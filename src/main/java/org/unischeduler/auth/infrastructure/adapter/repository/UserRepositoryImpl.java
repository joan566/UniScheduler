package org.unischeduler.auth.infrastructure.adapter.repository;

import org.springframework.stereotype.Repository;
import org.unischeduler.auth.domain.entity.User;
import org.unischeduler.auth.domain.outputports.UserRepository;
import org.unischeduler.auth.infrastructure.entity.UserEntity;
import org.unischeduler.auth.infrastructure.mapper.UserMapper;
import org.unischeduler.auth.infrastructure.repository.excel.ExcelUserRepository;
import org.unischeduler.auth.infrastructure.repository.posgresql.SpringDataUserRepository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final ExcelUserRepository excelUserRepository;
    private final SpringDataUserRepository springDataUserRepository;


    public UserRepositoryImpl(ExcelUserRepository excelUserRepository,
                              SpringDataUserRepository springDataUserRepository) {
        this.excelUserRepository = excelUserRepository;
        this.springDataUserRepository = springDataUserRepository;
    }

    @Override
    public User save(User user) {
        return UserMapper.toDomain(
                springDataUserRepository.save(
                    UserMapper.toEntity(user)
                )
        );
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<UserEntity> entityOptional = springDataUserRepository.findByEmail(email);

        if(entityOptional.isEmpty()) {
            return Optional.empty();
        }
        UserEntity entity = entityOptional.get();

        return Optional.of(UserMapper.toDomain(entity));
    }

    @Override
    public Optional<User> findById(Long id) {
        Optional<UserEntity> entityOptional = springDataUserRepository.findById(id);
        if(entityOptional.isEmpty()) {
           return Optional.empty();
        }

        UserEntity entity = entityOptional.get();
        return Optional.of(UserMapper.toDomain(entity));
    }

    @Override
    public boolean existsByEmail(String email) {
        return springDataUserRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByDocumentNumber(String documentNumber) {
        return springDataUserRepository.existsByDocumentNumber(documentNumber);
    }
}
