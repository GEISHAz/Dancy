package com.ssafy.dancy.config.security;

import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.message.response.auth.JwtTokenResponse;
import com.ssafy.dancy.repository.RedisRepository;
import com.ssafy.dancy.repository.UserRepository;
import com.ssafy.dancy.type.JwtCode;
import com.ssafy.dancy.type.Role;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Set;

@Component
@Slf4j
public class JwtTokenProvider {

    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;
    private final RedisRepository redisRepository;

    private String secretKey;

    public JwtTokenProvider(@Value("${jwt.secret.key}")String secretKey,
                            UserDetailsService userDetailsService, UserRepository userRepository, RedisRepository redisRepository){
        this.secretKey = secretKey;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.redisRepository = redisRepository;
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

        try{
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
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
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String makeRefreshToken(String email){
        Claims claims = Jwts.claims().setSubject(email);
        Date now = new Date();

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + refreshTokenValidTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        redisRepository.saveRefreshToken(email, token, 15);
        return token;
    }

    public void setRefreshTokenForClient(HttpServletResponse response, User user) {
        ResponseCookie cookie = ResponseCookie.from("refreshToken", makeRefreshToken(user.getEmail()))
                .maxAge(refreshTokenValidTime / 1000)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .sameSite("None")
                .build();

        response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }

    public JwtTokenResponse makeJwtTokenResponse(User user) {
        String accessToken = makeAccessToken(user.getEmail(), user.getRoles());
        return JwtTokenResponse.builder()
                .accessToken(accessToken)
                .tokenType(tokenType)
                .authType(user.getAuthType())
                .build();
    }

    private String makeAccessToken(String email, Set<Role> roles) {
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("roles", roles);

        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
