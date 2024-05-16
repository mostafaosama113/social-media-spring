package com.app.social.social.app.repositry;

import com.app.social.social.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User , Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}
