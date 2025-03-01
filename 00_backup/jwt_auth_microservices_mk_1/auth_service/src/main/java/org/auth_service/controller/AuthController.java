package org.auth_service.controller;


import org.auth_service.entity.User;
import org.auth_service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> userRegistration(@RequestBody User user) {
        return ResponseEntity.ok(authService.newUserRegistration(user));
    }

    @GetMapping("/t")
    public String test(){
        return "Auth controller test endpoint";
    }
}
