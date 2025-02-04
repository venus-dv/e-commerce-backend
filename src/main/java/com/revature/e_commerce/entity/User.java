package com.revature.e_commerce.entity;

import com.revature.e_commerce.enums.UserRole;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Represents a user in the e-commerce platform.
 *
 * <p>The user can be a buyer, seller, or admin, and their role is stored in the 'role' field.
 * The user also has an associated email, password (hashed), and a first and last name.
 * The 'date_joined' field captures the registration timestamp.</p>
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRole role;

    @Column(name = "date_joined", nullable = false)
    private LocalDateTime dateJoined;

    // Getters and setters

    /**
     * Get the user ID.
     *
     * @return the user ID
     */
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Get the user's email.
     *
     * @return the user's email
     */
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the user's hashed password.
     *
     * @return the user's password
     */
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get the user's first name.
     *
     * @return the user's first name
     */
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get the user's last name.
     *
     * @return the user's last name
     */
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get the user's role (USER, ADMIN, SELLER).
     *
     * @return the user's role
     */
    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    /**
     * Get the date the user joined the platform.
     *
     * @return the date the user joined
     */
    public LocalDateTime getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(LocalDateTime dateJoined) {
        this.dateJoined = dateJoined;
    }
}
