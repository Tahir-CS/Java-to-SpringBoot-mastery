package com.university.studentapi.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.university.studentapi.security.Role;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

/**
 * Lesson 3: JWT Token Generation & Validation.
 *
 * A JWT token is like a digital ID card:
 * - Header (algorithm, type)
 * - Payload (claims: email, expiry, etc.)
 * - Signature (proves nobody tampered with it)
 *
 * Flow:
 * 1. User logs in → JwtTokenProvider generates a token
 * 2. Client stores token (localStorage)
 * 3. Client sends token in Authorization header
 * 4. JwtTokenProvider validates token signature + expiry
 */
@Component
public class JwtTokenProvider {

    @Value("${jwt.secret:student-management-api-demo-secret-key-change-this-is-long-enough-for-hs512}")
    private String secretKey;

    @Value("${jwt.expiration:86400000}") // 24 hours in ms
    private long expirationMs;

        /**
         * Generate a JWT token with role claims for RBAC.
         */
        public String generateToken(Long userId, String email, Role role) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());

        return Jwts.builder()
            .claims(Map.of(
                "uid", userId,
                "role", role.name()
            ))
                .subject(email) // Who is this token for?
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key, SignatureAlgorithm.HS512) // Sign with secret key
                .compact();
    }

    /**
     * Validate token signature & expiry.
     * Returns true if valid, false if tampered or expired.
     */
    public boolean validateToken(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Extract email from token payload.
     * Returns null if token is invalid.
     */
    public String extractEmail(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
            Claims claims = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return claims.getSubject();
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Extract role from token claims for authorization checks.
     */
    public String extractRole(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
            Claims claims = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            Object role = claims.get("role");
            return role == null ? Role.USER.name() : role.toString().toUpperCase();
        } catch (Exception ex) {
            return Role.USER.name();
        }
    }
}
