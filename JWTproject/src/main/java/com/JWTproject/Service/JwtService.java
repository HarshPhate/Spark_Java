package com.JWTproject.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

import java.nio.charset.StandardCharsets;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private Long jwtExpirationTime;

    // Generate JWT Token
    public String generateToken(String username) {

        return generateToken(new HashMap<>(), username);
    }

    // Main Token Method
    private String generateToken(
            Map<String, Object> extraClaims,
            String username
    ) {

        return Jwts.builder()

                .claims(extraClaims)

                .subject(username)

                .issuedAt(
                        new Date(System.currentTimeMillis())
                )

                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + jwtExpirationTime
                        )
                )

                .signWith(getSignKey())

                .compact();
    }

    // Extract Username
    public String extractUsername(String token) {

        return extractClaim(
                token,
                Claims::getSubject
        );
    }

    // Generic Claim Extract Method
    public <T> T extractClaim(
            String token,
            Function<Claims, T> claimsFunction
    ) {

        final Claims claims =
                extractAllClaims(token);

        return claimsFunction.apply(claims);
    }

    // Validate Token
    public boolean isTokenValid(
            String token,
            UserDetails userDetails
    ) {

        final String username =
                extractUsername(token);

        return username.equals(
                userDetails.getUsername()
        ) && !isTokenExpired(token);
    }

    // Check Expiration
    private boolean isTokenExpired(String token) {

        return extractExpiration(token)
                .before(new Date());
    }

    // Extract Expiration
    public Date extractExpiration(String token) {

        return extractClaim(
                token,
                Claims::getExpiration
        );
    }

    // Extract All Claims
    private Claims extractAllClaims(String token) {

        return Jwts.parser()

                .verifyWith(getSignKey())

                .build()

                .parseSignedClaims(token)

                .getPayload();
    }

    // Secret Key
    private SecretKey getSignKey() {

        byte[] bytes =
                secretKey.getBytes(
                        StandardCharsets.UTF_8
                );

        return Keys.hmacShaKeyFor(bytes);
    }

    public Long getJwtExpirationTime() {
        return jwtExpirationTime;
    }
}