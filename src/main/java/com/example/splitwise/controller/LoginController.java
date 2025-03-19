package com.example.splitwise.controller;
import com.example.splitwise.DTO.LoginResponse;
import com.example.splitwise.service.UserValidationService;
import com.example.splitwise.service.security.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final UserValidationService userValidationService;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginController(UserValidationService userValidationService, JwtTokenProvider jwtTokenProvider) {
        this.userValidationService = userValidationService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/valid-login")
    public ResponseEntity<?> validLogin(@RequestParam String email, @RequestParam String password) {
        if (userValidationService.validateUser(email, password)) {
            String token = jwtTokenProvider.generateToken(email);
            return ResponseEntity.ok(new LoginResponse(token));
        }
        return ResponseEntity.ok("Invalid Credentials");
    }
}
