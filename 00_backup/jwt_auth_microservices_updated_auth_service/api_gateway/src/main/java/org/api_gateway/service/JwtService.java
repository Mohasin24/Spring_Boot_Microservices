package org.api_gateway.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

//@Service
public class JwtService {
    private final String secretKey = "a8b55a0dc5b7e4891668095264a2156ec456721968c032306ca8f236648acedbb0f48d2e56311a11fbbd0b9aa7c4aa03a19e6948334416bf9d2395980a22e3b88f6d897691e98e3b672e7099ca563cd61ca89b8a615eb115645b97deaf4a308d88f48619b80972c2479263fcd7e7ae1f410239ea73c8ef59affbb9e7aa8da2e3ef154bf5f7f5ea95f104213222f9eca1337787a1a707929eb4b0d0ec6f3da17c02c657b1a478af8fb8f74746b060471d2b2ffbdc3f6edb94b3a2a348c2cfc282ea9b207754e0563643598af6540fcba85489fe019921650eeccdf1aa3924d38d26b801dc49faa406127f3abd2592ba28b4ea1dac1c0ff97b74dc35077d836398";

    public String generateToken(String username){
        Map<String,Object> claims = new HashMap<>();

        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 100*60*60*60))
                .signWith(getKey())
                .compact();
    }

    public SecretKey getKey(){
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsernameFromToken(String token){
        return extractClaims(token, Claims::getSubject);
    }

    private <T> T extractClaims(String token, Function<Claims,T> claimResolver) {
        final Claims claims = extractAllClaims(token);

        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private boolean isTokenExpired(String token) {
        boolean result= extractTokenExpiration(token).before(new Date());
        System.err.println("extractTokenExpiration: "+result);
        return result;
    }

    private Date extractTokenExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    public boolean isTokenValid(String token){
        System.err.println("Is token expired: "+isTokenExpired(token));
        return isTokenExpired(token);
    }

}
