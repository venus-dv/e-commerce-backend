package com.revature.e_commerce.repository;

import com.revature.e_commerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for User entity.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by email.
     *
     * @param email The user's email.
     * @return An optional user if found.
     */
    Optional<User> findByEmail(String email);

    /**
     * Checks if a user exists with the given email.
     *
     * @param email The email to check.
     * @return True if a user exists, otherwise false.
     */
    boolean existsByEmail(String email);
}
