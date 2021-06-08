package com.creepy.creepycore.api.users.repositories;

import com.creepy.creepycore.shared.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    User findByUuid(String uuid);
}
