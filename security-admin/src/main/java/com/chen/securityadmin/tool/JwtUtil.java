package com.chen.securityadmin.tool;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.SignatureException;
import java.util.Date;
import java.util.Map;

/**
 * @author chenyingtao
 * @version 1.0
 * @projectName SpringAIDemo
 * @package com.chen.securityadmin.tool
 * @className com.chen.securityadmin.tool.JwtUtil
 * @date 2025/4/10 21:45
 * @description @todo
 */
//@Component
public class JwtUtil {


    private final JwtProperties jwtProperties;
    private final byte[] secretKeyBytes;

    public JwtUtil(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
        this.secretKeyBytes = jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8);
        validateSecretKeyLength();
    }

    private void validateSecretKeyLength() {
        if (secretKeyBytes.length < 32) {
            throw new IllegalArgumentException("HS256 algorithm requires at least 32 bytes secret key");
        }
    }

    public String createJWT(Map<String, Object> claims, long ttlMillis) {
//        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
//        long expMillis = System.currentTimeMillis() + ttlMillis;
//        Date exp = new Date(expMillis);
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .setExpiration(exp)  // 确保在 setClaims 之后调用
//                .signWith(signatureAlgorithm, secretKeyBytes)
//                .compact();
        return  null;
    }

    public Claims parseJWT(String token) {
//        return Jwts.parser()
//                .setSigningKey(secretKeyBytes)
//                .parseClaimsJws(token)
//                .getBody();
        return null;
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        try {
            final String username = getUsernameFromToken(token);
            return username != null
                    && username.equals(userDetails.getUsername())
                    && !isTokenExpired(token);
        } catch (JwtException | NullPointerException e) {
            return false;
        }
    }

    private String getUsernameFromToken(String token) {
        Claims claims = parseJWT(token);
        return claims.getSubject();  // 允许返回 null，由调用方处理
    }

    private boolean isTokenExpired(String token) {
        try {
            final Date expiration = parseJWT(token).getExpiration();
            return expiration.before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        }
    }
}

