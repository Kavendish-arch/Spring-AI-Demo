package com.chen.securityadmin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
