package org.auth_service.controller;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.auth_service.entity.User;
import org.auth_service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class  AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService  authService){
        this.authService=authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(HttpServletResponse response, @RequestBody User user){

        String token = authService.verifyUser(user);

        Cookie cookie = new Cookie("token", token);

        cookie.setMaxAge(7 * 24 * 60 * 60); // 7 days
        cookie.setHttpOnly(true); // Prevent access from JavaScript
        cookie.setSecure(true); // Only send over HTTPS
        cookie.setPath("/"); // Available for all endpoints
        cookie.setAttribute("SameSite","Strict"); // CSRF Protection

        // Add cookie to response
        response.addCookie(cookie);

        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<User> userRegistration(@RequestBody User user){
        return ResponseEntity.ok(authService.newUserRegistration(user));
    }
}
