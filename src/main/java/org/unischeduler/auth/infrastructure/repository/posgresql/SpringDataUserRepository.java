package org.unischeduler.auth.infrastructure.repository.posgresql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unischeduler.auth.infrastructure.entity.UserEntity;

import java.util.Optional;

public interface SpringDataUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByDocumentNumber(String documentNumber);
}
