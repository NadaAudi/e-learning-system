package com.example.auth_service.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiry}")
    private long expiry;

    private Key signingKey;

    @PostConstruct
    public void init() {
        if (secret == null || secret.length() < 32) {
            System.err.println("❌ JWT secret is null or too short! Minimum 32 characters required.");
        } else {
            this.signingKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
            System.out.println("✅ JWT signing key initialized");
        }
    }

    public String generateToken(String username, String role) {
        try {
            return Jwts.builder()
                    .setSubject(username)
                    .claim("role", role)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + expiry))
                    .signWith(signingKey, SignatureAlgorithm.HS512)
                    .compact();
        } catch (Exception e) {
            System.err.println("❌ Failed to generate token: " + e.getMessage());
            throw new RuntimeException("Token generation failed", e);
        }
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public String extractRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    public boolean validateToken(String token) {
        try {
            extractAllClaims(token); // will throw if invalid
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            System.err.println("❌ Invalid token: " + e.getMessage());
            return false;
        }
    }
}
