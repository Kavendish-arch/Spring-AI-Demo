package com.chen.securityadmin.tool;

import com.chen.securityadmin.entiry.po.DUsers;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenyingtao
 * @version 1.0
 * @projectName SpringAIDemo
 * @package com.chen.securityadmin.tool
 * @className com.chen.securityadmin.tool.JwtUtil2
 * @date 2025/4/11 11:44
 * @description @todo
 */
@Component
public class JwtUtil2 extends JwtUtil{

    /**
     * 过期时间，7天
     */
    private static final long EXPIRE = 60000 * 60 * 24 * 7;
    /**
     * 加密密钥
     */
    private static final String SECRET = "net.example.SSMdemo";
    /**
     * 使用HS256算法生成密钥
     */
    private static final SecretKey KEY = Jwts.SIG.HS256.key().random(new SecureRandom(SECRET.getBytes())).build();
    /**
     * 主题
     */
    private static final String SUBJECT = "SSMdemo";

    public JwtUtil2(JwtProperties jwtProperties) {
        super(jwtProperties);
    }


    public static String geneJsonWebToken(Map<String, Object> claims) {

        // 三部分
        // 替换原密钥生成逻辑
        long expMillis = System.currentTimeMillis() + EXPIRE;
        Date exp = new Date(expMillis);

        return Jwts.builder().claims()
                .add(claims)  // 添加内容
                .subject(SUBJECT)   // 声明主题
                .issuedAt(new Date())   // 创建JWT时的时间戳
                .expiration(exp)  // 设置过期时间
                .and()  // 返回JwtBuilder配置
                .signWith(KEY)  // 签名
                .compact(); // 紧凑
    }
//    校验JWT令牌：

    public static Claims checkJWT(String token) {
        try {
            final Claims claims = Jwts.parser()
                    .verifyWith(KEY)    // 验证所有遇到的JWS签名
                    .build()
                    .parse(token).accept(Jws.CLAIMS)   // 解析jws
                    .getPayload();  // JWT有效载荷
            return claims;
        } catch (Exception e) {
            return null;
        }
    }
}
