package com.example.property_management.jwt;

import io.jsonwebtoken.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtils implements Serializable {

    private static final String TOKEN_SECRET = "This is secret";

    private static final long ACCESS_TOKEN_EXP = 1000*60*15;
    private static final long REFRESH_TOKEN_EXP = 1000*60*60*8;

    public Claims getALlClaimsFromToken(String token){
        return Jwts.parser()
                .setSigningKey(TOKEN_SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    public <T>T getClaimFromToken(String token, Function<Claims,T> claimResolver){
        final Claims claims = getALlClaimsFromToken(token);
        return claimResolver.apply(claims);
    }

    public String getUsernameFromToken(String token){
        return getClaimFromToken(token,Claims::getSubject);
    }

    public Date getExpiratioTimeFromToken(String token){
        return getClaimFromToken(token,Claims::getExpiration);
    }

    public boolean isTokenExpired(String token){
        Date expDate = getExpiratioTimeFromToken(token);
        return expDate.before(new Date());
    }

    public boolean validateToken(String token){
        try{
            Jwts.parser()
                    .setSigningKey(TOKEN_SECRET)
                    .parseClaimsJws(token);
            return true;
        }catch(SignatureException| MalformedJwtException | ExpiredJwtException |
                UnsupportedJwtException | IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public String generateToken(String email){
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+ACCESS_TOKEN_EXP))
                .signWith(SignatureAlgorithm.HS256,TOKEN_SECRET)
                .compact();
    }

    public String generateRefreshToken(String email){
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+REFRESH_TOKEN_EXP))
                .signWith(SignatureAlgorithm.HS256,TOKEN_SECRET)
                .compact();
    }


}
