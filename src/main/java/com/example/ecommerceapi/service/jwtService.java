package com.example.ecommerceapi.service;

import com.example.ecommerceapi.modal.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Service
public class jwtService {

    private static final String secretKey = "manikandanisveryverygoodboyandbadbojnjjjjjnkiinbhgffvgvvbffggvfhgtttderfhbhbjnnnbcfftygbcfdfygubfxtfygby";

    public String generateToken(User user) {

        String token = Jwts.builder()
                .claims()
                .subject(user.getUsername())
                .expiration(new Date(System.currentTimeMillis() + 1000L*60*60*30))
                .issuedAt(new Date(System.currentTimeMillis()))
                .and()
                .signWith(genKey())
                .compact();
        return token;
    }

    public SecretKey genKey(){
        byte[] key =Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(key);
    }

    public String getUsernameByToken(String token) {
        Claims claim = ExtractUsername(token);
        return claim.getSubject();
    }

    public Claims ExtractUsername(String token){
        Claims claim = Jwts.parser()
                .verifyWith(genKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claim;
    }
}
