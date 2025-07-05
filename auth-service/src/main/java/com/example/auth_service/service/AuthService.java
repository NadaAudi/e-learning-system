package com.example.auth_service.service;

import com.example.auth_service.model.Role;
import com.example.auth_service.model.User;
import com.example.auth_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class AuthService {
    private static final Logger logger = Logger.getLogger(AuthService.class.getName());

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(String username, String password, Role role) {
        logger.info("Registering user: " + username);
        if (userRepository.findByUsername(username).isPresent()) {
            logger.warning("Username already exists: " + username);
            throw new RuntimeException("Username already exists");
        }
        User user = new User();
        user.setUsername(username);
        String encodedPassword = passwordEncoder.encode(password);
        logger.info("Encoded password for " + username + ": " + encodedPassword);
        user.setPassword(encodedPassword);
        user.setRole(role);
        userRepository.save(user);
        logger.info("User registered successfully: " + username);
    }

    public User authenticate(String username, String password) {
        logger.info("Authenticating user: " + username);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    logger.warning("User not found: " + username);
                    return new RuntimeException("Invalid credentials");
                });
        logger.info("Stored password: " + user.getPassword());
        logger.info("Provided password: " + password);
        boolean passwordMatch = passwordEncoder.matches(password, user.getPassword());
        logger.info("Password match result: " + passwordMatch);
        if (!passwordMatch) {
            logger.warning("Password mismatch for user: " + username);
            throw new RuntimeException("Invalid credentials");
        }
        logger.info("User authenticated successfully: " + username);
        return user;
    }

    public User getUserById(Long id) {
        logger.info("Fetching user with ID: " + id);
        return userRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warning("User not found with ID: " + id);
                    return new RuntimeException("User not found");
                });
    }
}