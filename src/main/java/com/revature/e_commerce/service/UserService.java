package com.revature.e_commerce.service;

import com.revature.e_commerce.entity.User;
import com.revature.e_commerce.entity.Product;
import com.revature.e_commerce.entity.Order;
import com.revature.e_commerce.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service layer for handling user-related operations.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder; // Needed for password encryption

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registers a new user in the system.
     *
     * @param user The user object containing registration details.
     * @return The registered user with an assigned ID.
     */
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt password
        return userRepository.save(user);
    }

    /**
     * Logs in a user by verifying credentials.
     *
     * @param email The user's email.
     * @param password The user's password.
     * @return A dummy token (Replace with JWT or session token implementation).
     */
    public String loginUser(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return "dummy-token"; // Replace with real authentication
            }
        }
        throw new RuntimeException("Invalid credentials");
    }

    /**
     * Retrieves product details by product ID.
     *
     * @param productId The ID of the product.
     * @return The product object if found.
     */
    public Product getProductDetails(Long productId) {
        // TODO: Implement fetching product details from the product repository
        return new Product();
    }

    /**
     * Adds a product to the user's cart.
     *
     * @param productId The ID of the product to add.
     * @param quantity The quantity to be added.
     */
    public void addProductToCart(Long productId, int quantity) {
        // TODO: Implement cart functionality
    }

    /**
     * Places an order for the buyer.
     *
     * @param order The order details.
     * @return The placed order.
     */
    public Order placeOrder(Order order) {
        // TODO: Implement order placement logic
        return order;
    }

    /**
     * Retrieves all orders for a user.
     *
     * @return List of orders.
     */
    public List<Order> getOrders() {
        // TODO: Fetch orders from the database
        return List.of();
    }

    /**
     * Adds a new product to the seller's inventory.
     *
     * @param product The product details.
     * @return The added product.
     */
    public Product addProductToInventory(Product product) {
        // TODO: Implement product addition logic
        return product;
    }
}
