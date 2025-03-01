package com.auth_api.spring_jwt_auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {

//    @Value("${JWT.SECRET.KEY}")
//    private String secretKey;

    private final String secretKey="2eec151e8c4fbca5c1b3e3e77832a6e7a19c59fd6c677350251e81abdcbd294ffa4725785cf4f1b2d35313a8896f6736fdbf1e064eeef370245bd47cc91de52c4d6b412a9f93a73a9ab90f9d8d4cd1e3090da4d23ea0fb8638f0bf55e02db366270ec17be1e1722438650152a35e01324491ffaf4a5fa2f5f49a704d21f54d9c1c854954f77b6c53080ae7d7e5563a93042f2fe5bf04a5a5e5a91c8ff01ea9288df6a4fe008071f7a68e6524998ecbe45a0db3b1c119cc90375652fe6469e585d43f533f40abf8cc46f13aee716cf38aa31a522347f60bddc3366695c97c6e6a574c290785e5dc19aed0020698b0abfff857cc8ef2d584f56824291dc5720225";

    public JwtService() {
//        try {
//            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
//            SecretKey key = keyGen.generateKey();
//            secretKey = Base64.getEncoder().encodeToString(key.getEncoded());
//        } catch (NoSuchAlgorithmException exception) {
//            throw new RuntimeException(exception.getMessage());
//        }
    }

    public String generateToken(String username) {
        Map<String, Objects> claims = new HashMap<>();

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getKey())
                .compact();
    }


    public SecretKey getKey() {
        byte[] keyBytes = secretKey.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsernameFromToken(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsernameFromToken(token);
//        return (userDetails.getUsername().equals(username)&& !isTokenExpired(token));
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token) {
        return extractTokenExpiration(token).before(new Date());
    }

    private Date extractTokenExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
