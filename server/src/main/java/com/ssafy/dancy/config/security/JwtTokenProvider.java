package com.ssafy.dancy.config.security;

import com.ssafy.dancy.repository.UserRepository;
import com.ssafy.dancy.type.JwtCode;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
@Slf4j
public class JwtTokenProvider {

    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;

    private String secretKey;

    public JwtTokenProvider(@Value("$jwt.secret.key}")String secretKey,
                            UserDetailsService userDetailsService, UserRepository userRepository){
        this.secretKey = secretKey;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
    }

    public static long tokenValidTime = 30 * 60 * 1000L; // 30분
    public static long refreshTokenValidTime = 15 * 60 * 60 * 24 * 1000L; // 15일

    private String tokenType = "Bearer";
    private String prefix = "Bearer ";


    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("AUTH-TOKEN");
    }

    public JwtCode validateToken(String token) {
        if(token == null){
            return JwtCode.DENIED;
        }

        Key encodedKey = getKeyFromBase64EncodedKey(secretKey);

        try{
            Jwts.parserBuilder().setSigningKey(encodedKey).build().parseClaimsJws(token);
            return JwtCode.ACCESS;
        }catch(ExpiredJwtException e){
            return JwtCode.EXPIRED;
        }catch(JwtException | IllegalArgumentException e){
            log.info("잘못된 JWT 서명입니다.");
        }

        return JwtCode.DENIED;
    }

    private Key getKeyFromBase64EncodedKey(String secretKey){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);

        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPrimaryKey(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private String getUserPrimaryKey(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKeyFromBase64EncodedKey(secretKey))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
