package com.university.studentapi.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

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

    @Value("${jwt.secret:my-secret-key-change-this-in-production}")
    private String secretKey;

    @Value("${jwt.expiration:86400000}") // 24 hours in ms
    private long expirationMs;

    /**
     * Generate a JWT token for a user (by email).
     * Token expires after 24 hours.
     */
    public String generateToken(String email) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());

        return Jwts.builder()
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
}
