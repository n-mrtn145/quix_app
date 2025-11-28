package com.quix.quix.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class SupabaseJwtService {

    @Value("${supabase.jwt-secret}")
    private String secret;

    public Claims validate(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secret.getBytes(StandardCharsets.UTF_8))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUserId(Claims claims) {
        return claims.get("sub", String.class);
    }

    public String getEmail(Claims claims) {
        return claims.get("email", String.class);
    }

    public String getRole(Claims claims) {
        return claims.get("role", String.class);
    }
}
