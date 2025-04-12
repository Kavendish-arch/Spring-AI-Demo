package com.chen.securityadmin.tool;

import io.jsonwebtoken.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.SignatureException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @author chenyingtao
 * @version 1.0
 * @projectName SpringAIDemo
 * @package com.chen.securityadmin.tool
 * @className com.chen.securityadmin.tool.JwtUtil
 * @date 2025/4/10 21:45
 * @description @todo
 */
@Component
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
        JwtBuilder jwtBuilder = Jwts.builder();
        // 三部分
        // 替换原密钥生成逻辑
//        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // 自动生成256位安全密钥

        long expMillis = System.currentTimeMillis() + ttlMillis;
        Date exp = new Date(expMillis);

        String jwtToken = jwtBuilder
                .setHeaderParam("type", "JWT")
                .setHeaderParam("alg", "HS256")
                .claim("name", "chen")
                .claim("role", "admin")
                .setSubject("admin-test")
                .setExpiration(exp)
                .setId(UUID.randomUUID().toString())
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();
        System.out.println(jwtToken);
        return jwtToken;
    }

    public Claims parseJWT(String token) {
        // 复用解析器实例
        JwtParser jwtParser = Jwts.parser().setSigningKey(jwtProperties.getSecretKey()).build();
        Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);

        Claims body = claimsJws.getBody();
        return body;
    }

    /**
     * 判断token是否正确
     * @param token
     * @param userDetails
     * @return
     */
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

    /**
     *
     *
     * @param token
     * @return
     */
    private String getUsernameFromToken(String token) {
        Claims claims = parseJWT(token);
        return claims.getSubject();  // 允许返回 null，由调用方处理
    }

    /**
     * 判断token是否过期
     *
     * @param token
     * @return
     */
    private boolean isTokenExpired(String token) {
        try {
            final Date expiration = parseJWT(token).getExpiration();
            return expiration.before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        }
    }
}

