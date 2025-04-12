package com.chen.securityadmin.controller;

import com.chen.securityadmin.service.DUsersService;
import com.chen.securityadmin.tool.JwtProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.stream.Collectors;

/**
 * @author chenyingtao
 * @version 1.0
 * @projectName SpringAIDemo
 * @package com.chen.securityadmin.controller
 * @className com.chen.securityadmin.controller.TokenController
 * @date 2025/4/11 15:34
 * @description @todo
 */
@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private JwtEncoder jwtEncoder;


    private DUsersService dUsersService;

    /**
     * 生成JWT令牌
     *
     * @param authentication Spring Security认证对象，包含当前用户认证信息
     *                         - 通过getName()获取用户名
     *                         - 通过getAuthorities()获取用户权限集合
     * @return 完整签发的JWT令牌字符串
     */
    @PostMapping("/get_token")
    public String token(Authentication authentication) {

        System.out.println("---------------------------");
        System.out.println(authentication.getName());
        System.out.println(authentication);
        System.out.println("token 接口" + authentication);
        System.out.println("---------------------------");
        // 初始化时间基准点
        Instant now = Instant.now();

        // 从配置获取JWT有效期（单位：秒）
        long expiry = jwtProperties.getTtl();

        // 将用户权限集合转换为空格分隔的字符串
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        // 构建JWT声明集
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")                // 签发者标识
                .issuedAt(now)                 // 签发时间
                .expiresAt(now.plusSeconds(expiry)) // 过期时间
                .subject(authentication.getName()) // 用户主体标识
                .claim("scope", scope)         // 权限范围声明
                .build();
        // @formatter:on

        // 编码JWT声明集并返回完整令牌字符串
        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }



}
