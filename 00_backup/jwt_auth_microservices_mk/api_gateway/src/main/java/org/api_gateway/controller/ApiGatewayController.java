package org.api_gateway.controller;

import org.api_gateway.service.JwtService;
import org.api_gateway.util.UserRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/gateway")
public class ApiGatewayController {
    private final JwtService jwtService;

    @Autowired
    public ApiGatewayController(JwtService jwtService){
        this.jwtService=jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> userLogin(@RequestBody UserRecord userRecord){
        String token = jwtService.generateToken(userRecord.username());

        return ResponseEntity.ok(token);
    }
}
