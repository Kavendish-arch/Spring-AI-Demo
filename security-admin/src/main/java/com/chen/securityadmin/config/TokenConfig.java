package com.chen.securityadmin.config;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
* @projectName SpringAIDemo
* @package com.chen.securityadmin.config
* @className com.chen.securityadmin.config.TokenConfig

* @author chenyingtao
* @date 2025/4/10 21:51
* @version 1.0
* @description @todo 
*/
@Configuration
public class TokenConfig {

//    private String SIGNING_KEY = "mq123";
//
//    @Autowired
//    TokenStore tokenStore;
//
////    @Bean
////    public TokenStore tokenStore() {
////        //使用内存存储令牌（普通令牌）
////        return new InMemoryTokenStore();
////    }
//
//    @Autowired
//    private JwtAccessTokenConverter accessTokenConverter;
//
//    @Bean
//    public TokenStore tokenStore() {
//        return new JwtTokenStore(accessTokenConverter());
//    }
//
//    @Bean
//    public JwtAccessTokenConverter accessTokenConverter() {
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        converter.setSigningKey(SIGNING_KEY);
//        return converter;
//    }
//
//    //令牌管理服务
//    @Bean(name="authorizationServerTokenServicesCustom")
//    public AuthorizationServerTokenServices tokenService() {
//        DefaultTokenServices service=new DefaultTokenServices();
//        service.setSupportRefreshToken(true);//支持刷新令牌
//        service.setTokenStore(tokenStore);//令牌存储策略
//
//        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
//        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter));
//        service.setTokenEnhancer(tokenEnhancerChain);
//
//        service.setAccessTokenValiditySeconds(7200); // 令牌默认有效期2小时
//        service.setRefreshTokenValiditySeconds(259200); // 刷新令牌默认有效期3天
//        return service;
//    }

}
