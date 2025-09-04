package com.webAppG9.backend.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    // atributos
    // valor en codigo fuente
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    // fcion para generar la llave de firma del token JWT
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    // metodo para generar el token
    // dandole todos los propiedaes para su definicion
    public String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey())
                .compact();
    }

    // metodo pára saber si el token contiene el role ADMIN O USER
    public String extractRole(String token) {
        return extractClaims(token).get("role", String.class);
    }

    // metodo para validar y decodificar el contenido del token
    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // validar token si es igula al usuario logueado y no ha expirado
    public boolean isTokenValid(String token, String email) {
        Claims claims = extractClaims(token);
        return claims.getSubject().equals(email) &&
                !claims.getExpiration().before(new Date());
    }

}