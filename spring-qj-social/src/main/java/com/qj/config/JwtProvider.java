package com.qj.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtProvider {

    private static final SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    // Generate JWT token
    public static String generateToken(Authentication auth) {
        return Jwts.builder()
                   .setIssuer("qj")
                   .setIssuedAt(new Date())
                   .setExpiration(new Date(new Date().getTime() + 86400000))
                   .claim("email", auth.getName())
                   .signWith(key)
                   .compact();
    }

    // Extract email from JWT token
    public static String getEmailFromJwtToken(String jwt) {
        if (jwt.startsWith("Bearer ")) {
            jwt = jwt.substring(7);
        }

        // Parse the token
        Claims claims = Jwts.parserBuilder()
                            .setSigningKey(key)
                            .build()
                            .parseClaimsJws(jwt)
                            .getBody();

        return String.valueOf(claims.get("email"));
    }
}