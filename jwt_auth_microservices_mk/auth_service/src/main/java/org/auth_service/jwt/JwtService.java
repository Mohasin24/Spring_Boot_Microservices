package org.auth_service.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {
    private final String secretKey="d3171c6bc56bda5b29e3c242946f0a92b6ac012023f0e9bbfd9cf87012795a167676a1e8faa7722c1beeb77a152be6620d4c424ee6d2789c1e00568b1ad859b7a2775c00a0d55093684cda1fcb5c51b8e05abe051bf51e1501ddec18c4b34165875b0d02d1e6acae0777beeb9950125ac5b8398e6286a607da3af77847834ca1ff55fe025f828eff5fd089a27ee7fc861b81d4048fdc52705212ed1e32443956a1946456458a8463f7014e6a06fe7dcad6326f5fc5a888d656fd120ba82c384bbd30cc32d7e3e8de8f6f11cc797bca25e9005a824d696c228e1adae221d5cc2fc157f249ea92d8c90700386ebe3de196908a1c435ae098cb4cf88aeaa3baea0a";
    public JwtService() {
    }

    public String generateToken(String username) {
        Map<String, Objects> claims = new HashMap<>();

        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getKey())
                .compact();
    }

    public SecretKey getKey() {
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsernameFromToken(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private boolean isTokenExpired(String token) {
        return extractTokenExpiration(token).before(new Date());
    }

    private Date extractTokenExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
