package com.example.userlogin.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Component
/*
 * FUNCIONALIDAD
 * Clase que implementa un componente de utilidad para la generación y validación de tokens JWT.
 * La clase utiliza la librería JJWT para la generación y validación de tokens JWT.
 */
public class JwtUtil {

    // Generar una clave secreta segura para HS256
    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Generar un token JWT
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username) // Establecer el nombre de usuario como sujeto del token
                .setIssuedAt(new Date()) // Fecha de emisión
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Expiración: 10 horas
                .signWith(SECRET_KEY) // Firmar el token con la clave secreta segura
                .compact();
    }

    // Extraer el nombre de usuario del token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extraer un claim genérico del token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extraer todos los claims del token JWT
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY) // Usar la clave secreta para descifrar el token
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Validar si el token es válido
    public boolean isTokenValid(String token, String username) {
        final String extractedUsername = extractUsername(token); // Extraer el nombre del usuario
        return (extractedUsername.equals(username) && !isTokenExpired(token)); // Validar usuario y expiración
    }

    // Validar si el token ha expirado
    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }
}