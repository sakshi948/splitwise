package com.example.splitwise.controller;
import com.example.splitwise.service.UserValidationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final UserValidationService userValidationService;

    public LoginController(UserValidationService userValidationService) {
        this.userValidationService = userValidationService;
    }

    @PostMapping("/valid-login")
    public ResponseEntity<String> validLogin(@RequestParam String email, @RequestParam String password) {
        if (userValidationService.validateUser(email, password)) {
            return ResponseEntity.ok("Login Successful");
        }
        return ResponseEntity.ok("Invalid Credentials");
    }
}
