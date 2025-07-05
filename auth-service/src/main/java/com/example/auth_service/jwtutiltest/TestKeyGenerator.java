package com.example.auth_service.jwtutiltest;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Base64;

public class TestKeyGenerator {
    public static void main(String[] args) {
        byte[] key = Keys.secretKeyFor(SignatureAlgorithm.HS512).getEncoded();
        System.out.println("✅ Use this secure JWT key:");
        System.out.println(Base64.getEncoder().encodeToString(key));
    }
}
