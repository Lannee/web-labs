package ru.lannee.web.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.SignatureException;
import java.util.Date;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    private static final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long expirationMs = 3600000; // Время действия токена: 1 час

    public String generateJwtToken(String username) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationMs);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(secretKey)
                .compact();
    }

    public String getUserNameFromJwtToken(String authToken) {
        try {
            return getParsedToken(authToken).getBody().getSubject();
        } catch (Exception e) {
            logger.error("Invalid token: {}", e.getMessage());

            return null;
        }
    }

    public boolean validateJwtToken(String authToken) {
        try {
            getParsedToken(authToken);
            return true;
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token: "+e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println("JWT token is expired: "+e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("JWT token is unsupported: "+e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty: "+e.getMessage());
        } catch (Exception other) {
            System.out.println("JWT other exception: " + other.getMessage());
        }
        return false;
    }

    private Jws<Claims> getParsedToken(String authToken) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(authToken);
    }

    public Claims getClaims(String jwtToken) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }
}