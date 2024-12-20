package com.ItInternship.Internship;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import io.jsonwebtoken.Jwts;

import java.util.Date;

@Component
public class JwtCore {
    @Value("${internship.app.secret}")
    private String secret;
    @Value("${internship.app.lifetimeMS}")
    private int lifetime;

    public String generateToken(Authentication authentication){
        UserDetailsImplementation userDetailsImplementation = (UserDetailsImplementation) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(userDetailsImplementation.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + lifetime))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
    public String getNameFromJwt(String token){

        return Jwts.parser()
                .setSigningKey(secret)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
