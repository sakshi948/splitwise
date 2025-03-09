package com.example.splitwise.service;

import com.example.splitwise.models.User;
import com.example.splitwise.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        System.out.println(user);
        return userRepository.save(user);
    }
}
