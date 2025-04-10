package com.chen.securityadmin.tool;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
* @projectName SpringAIDemo
* @package com.chen.securityadmin.tool
* @className com.chen.securityadmin.tool.JwtTest

* @author chenyingtao
* @date 2025/4/10 21:54
* @version 1.0
* @description @todo 
*/
@SpringBootTest
public class JwtTest {
    private long time = 60 * 1000 * 24;
    private String secret = "eyJuYW1lIjoiY2hlbiIsInJvbGUiOiJhZG1pbiIsInN1YiI6ImFkbWluLXRlc3QiLCJleHAiOjE3NDQyOTY3NzIsImp0aSI6ImVkYmZhMjAxLWFmZmYtNDNjZC04NmZjLTYzMzlhMmJlYTY5NSJ95jC2IjC_";
    @Test
    public void test() {
    }


    @Test
    public void jwtCreateTest(){
        JwtBuilder jwtBuilder = Jwts.builder();
        // 三部分
        Map<String, String> header = Map.of("alg", "HS256", "typ", "JWT");
        // 替换原密钥生成逻辑
//        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // 自动生成256位安全密钥


        String jwtToken = jwtBuilder
                .setHeaderParam("type", "JWT")
                .setHeaderParam("alg", "HS256")
                .claim("name", "chen")
                .claim("role", "admin")
                .setSubject("admin-test")
                .setExpiration(new Date(System.currentTimeMillis() + time))
                .setId(UUID.randomUUID().toString())
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
        System.out.println(jwtToken);
    }

    @Test
    public void parse(){

        String token = "eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJuYW1lIjoiY2hlbiIsInJvbGUiOiJhZG1pbiIsInN1YiI6ImFkbWluLXRlc3QiLCJleHAiOjE3NDQyOTY5MjQsImp0aSI6IjA0NTg3OGM2LWUxN2QtNDAxYS05MDU5LTI3YzAwOGZhYWY1MiJ9.QDDilwSp6VrZBr3FA-Lxh41czhtGRptuZ4tV9F8Irnk";
            // 复用解析器实例
        JwtParser jwtParser =  Jwts.parser().setSigningKey(secret).build();
        Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);

        Claims body = claimsJws.getBody();
        System.out.println(body.get("name"));
        System.out.println(body.get("role"));
        System.out.println(body.get("sub"));
        System.out.println(body.getExpiration());
        System.out.println(body.getId());

    }
}
