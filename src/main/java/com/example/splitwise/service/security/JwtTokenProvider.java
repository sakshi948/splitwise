package com.example.splitwise.service.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtTokenProvider {
    private final String jwtSecret = "adec413c04908f11cd52d788501d9b54b6cf0594340f7760337993ff44e7b0ed7ff894fafe79df5966b7ed76f25640d9f869fd6d4604cdbbc3568b44921294be0ea5da4dc710378d5acb6b30e385a1caec870986015f38f7cccd95631bdf37a8de89749d1a407d5b6618034b5b63a4c404e0e730567ec21c323423bcb1458faf";
    private final long jwtExpirationInMs = 3600000;

    public String generateToken(String email) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (Exception ex) {
        }
        return false;
    }
}
