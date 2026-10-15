package org.unischeduler.auth.domain.outputports;

import org.unischeduler.auth.domain.entity.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    User changePassword(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findById(String id);
    boolean existsByEmail(String email);
    boolean existsByDocumentNumber(String documentNumber);
}
