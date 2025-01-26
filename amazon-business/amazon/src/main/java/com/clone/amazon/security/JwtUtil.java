package com.clone.amazon.security;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

@Service
public class JwtUtil {

    private final JwtService jwtService;

    public JwtUtil(JwtService jwtService){
        this.jwtService=jwtService;
    }

    private String extractEmail(String token) {
        Claims claims = jwtService.extractAllClaims(token);
        return claims.get("email", String.class); // Assumes email is stored in the "email" claim
    }

}
