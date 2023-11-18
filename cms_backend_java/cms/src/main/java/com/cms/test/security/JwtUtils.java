package com.cms.test.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.security.KeyStore;
import java.util.Date;

@Component
public class JwtUtils {
    //private static final KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);
    private static  Key key = null;

    @Value("${spring.jwt.secretkey}")
    private String secret;
    @PostConstruct
    public void init(){
        byte[] keyBytes = Decoders.BASE64.decode(this.secret);
        key = Keys.hmacShaKeyFor(keyBytes);
    }


    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRATION);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt( new Date())
                .setExpiration(expireDate)
                .signWith(key,SignatureAlgorithm.HS512)
                .compact();
        System.out.println("New token :");
        System.out.println(token);
        return token;
    }
    public String getUsernameFromJWT(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        }
        catch (ExpiredJwtException expiredJwtException){
            throw new AuthenticationCredentialsNotFoundException("JWT was exprired");
        }
        catch (Exception ex) {
            throw new AuthenticationCredentialsNotFoundException("JWT was exprired or incorrect",ex.fillInStackTrace());
        }
    }
    public String generateToken(String email) {
        String username = email;
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRATION);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt( new Date())
                .setExpiration(expireDate)
                .signWith(key,SignatureAlgorithm.HS512)
                .compact();
        System.out.println("New token :");
        System.out.println(token);
        return token;
    }

}
