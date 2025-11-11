package com.quix.quix.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;

@Component
public class JwtUtils {

    private final String jwtSecret;

    public JwtUtils(@Value("${supabase.jwt-secret}")String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }

    public Claims validateToken(String token) throws Exception {
        try {
            Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return claims.getBody();
        } catch (Exception e) {
            throw new Exception("Invalid or expired token");
        }
    }
}
