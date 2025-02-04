package com.revature.e_commerce.controller;

import com.revature.e_commerce.entity.User;
import com.revature.e_commerce.entity.Product;
import com.revature.e_commerce.entity.Order;
import com.revature.e_commerce.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * UserController handles all actions related to a user account.
 * It supports both buyers and sellers and provides shared functionalities like registration, login,
 * and profile management.
 */
@RestController
@RequestMapping("/api/users")
@Tag(name = "User", description = "APIs related to the user account")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Register a new user (either buyer or seller) on the platform.
     *
     * @param user The user's registration details.
     * @return ResponseEntity containing the user object and HTTP status.
     */
    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Register a new user (buyer or seller) account")
    @ApiResponse(responseCode = "201", description = "User registered successfully")
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user) {
        User newUser = userService.registerUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    /**
     * Login a user (either buyer or seller) into the application using email and password.
     *
     * @param email The user's email.
     * @param password The user's password.
     * @return ResponseEntity containing the authentication token and HTTP status.
     */
    @PostMapping("/login")
    @Operation(summary = "Login a user", description = "Login with email and password for either buyer or seller")
    @ApiResponse(responseCode = "200", description = "Login successful")
    public ResponseEntity<String> loginUser(@RequestParam String email, @RequestParam String password) {
        String token = userService.loginUser(email, password);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    /**
     * View the details of a specific product.
     *
     * @param productId The product ID.
     * @return ResponseEntity containing the product object and HTTP status.
     */
    @GetMapping("/products/{productId}")
    @Operation(summary = "View product details", description = "View details of a specific product for buyers")
    @ApiResponse(responseCode = "200", description = "Product details fetched successfully")
    public ResponseEntity<Product> getProductDetails(@PathVariable Long productId) {
        Product product = userService.getProductDetails(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    /**
     * Add a product to the user's cart (either buyer).
     *
     * @param productId The product ID.
     * @param quantity The quantity to add.
     * @return ResponseEntity indicating success and HTTP status.
     */
    @PostMapping("/cart")
    @Operation(summary = "Add product to cart", description = "Add a product to the user's cart (only for buyers)")
    @ApiResponse(responseCode = "200", description = "Product added to cart successfully")
    public ResponseEntity<Void> addProductToCart(@RequestParam Long productId, @RequestParam int quantity) {
        userService.addProductToCart(productId, quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Place an order for a buyer (checkout).
     *
     * @param order The order details.
     * @return ResponseEntity containing the order and HTTP status.
     */
    @PostMapping("/orders")
    @Operation(summary = "Place an order", description = "Place an order with shipping and billing details " +
            "(only for buyers)")
    @ApiResponse(responseCode = "201", description = "Order placed successfully")
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
        Order newOrder = userService.placeOrder(order);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

    /**
     * View orders for a user (both buyers and sellers).
     *
     * @return ResponseEntity containing a list of orders and HTTP status.
     */
    @GetMapping("/orders")
    @Operation(summary = "View orders", description = "View all orders for the user " +
            "(both buyers and sellers can view their orders)")
    @ApiResponse(responseCode = "200", description = "Orders fetched successfully")
    public ResponseEntity<List<Order>> viewOrders() {
        List<Order> orders = userService.getOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    /**
     * Add a new product to the seller's inventory (only for sellers).
     *
     * @param product The product details.
     * @return ResponseEntity containing the added product and HTTP status.
     */
    @PostMapping("/products")
    @Operation(summary = "Add a product to inventory", description = "Add a new product to the seller's inventory " +
            "(only for sellers)")
    @ApiResponse(responseCode = "201", description = "Product added successfully")
    public ResponseEntity<Product> addProductToInventory(@RequestBody Product product) {
        Product newProduct = userService.addProductToInventory(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }
}
