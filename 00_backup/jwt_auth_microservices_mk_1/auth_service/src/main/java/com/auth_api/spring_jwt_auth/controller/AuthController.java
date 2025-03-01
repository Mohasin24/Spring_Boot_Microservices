package com.auth_api.spring_jwt_auth.controller;

import com.auth_api.spring_jwt_auth.entity.User;
import com.auth_api.spring_jwt_auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService  authService){
        this.authService=authService;
    }
    
    @GetMapping("/test")
    public String test(){
        return "AuthController Endpoint test";
    }
    @PostMapping("/login")
    public ResponseEntity<String> userLogin(@RequestBody User user){
        return ResponseEntity.ok(authService.verifyUser(user));
    }

    @PostMapping("/register")
    public ResponseEntity<User> userRegistration(@RequestBody User user){
        return ResponseEntity.ok(authService.newUserRegistration(user));
    }
}
