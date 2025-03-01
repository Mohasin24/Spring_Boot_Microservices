package org.api_gateway.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JwtService {

//    @Value("${JWT.SECRET.KEY}")
    private final String secretKey="2eec151e8c4fbca5c1b3e3e77832a6e7a19c59fd6c677350251e81abdcbd294ffa4725785cf4f1b2d35313a8896f6736fdbf1e064eeef370245bd47cc91de52c4d6b412a9f93a73a9ab90f9d8d4cd1e3090da4d23ea0fb8638f0bf55e02db366270ec17be1e1722438650152a35e01324491ffaf4a5fa2f5f49a704d21f54d9c1c854954f77b6c53080ae7d7e5563a93042f2fe5bf04a5a5e5a91c8ff01ea9288df6a4fe008071f7a68e6524998ecbe45a0db3b1c119cc90375652fe6469e585d43f533f40abf8cc46f13aee716cf38aa31a522347f60bddc3366695c97c6e6a574c290785e5dc19aed0020698b0abfff857cc8ef2d584f56824291dc5720225";

    public JwtService(){

    }
    public Claims extractClaims(String token){
        System.err.println("Inside the claims Api Gateway");
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean isTokenValid(String token){
        System.err.println("Inside the token valid Api Gateway");
        try {
            extractClaims(token);
            return true;
        } catch (SignatureException | IllegalArgumentException e) {
            return false;
        }
    }

    private SecretKey getKey(){
        byte[] keyBytes = secretKey.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
