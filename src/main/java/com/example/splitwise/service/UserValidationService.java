package com.example.splitwise.service;

import com.example.splitwise.models.User;
import com.example.splitwise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserValidationService {
    @Autowired
    private UserRepository userRepository;

    public boolean validateUser(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return user.getPassword().equals(password); // TODO: Use password hashing
        }
        return false;
    }
}
