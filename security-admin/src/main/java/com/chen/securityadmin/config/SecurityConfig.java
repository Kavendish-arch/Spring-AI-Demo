package com.chen.securityadmin.config;

import com.chen.securityadmin.filter.JwtFilter;
import com.chen.securityadmin.service.impl.UserDetailsServiceImpl;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * @author chenyingtao
 * @version 1.0
 * @projectName SpringAIDemo
 * @package com.chen.securityadmin.config
 * @className com.chen.securityadmin.config.SecurityConfig
 * @date 2025/4/10 16:36
 * @description @todo
 */
@Configuration
//@AllArgsConstructor
public class SecurityConfig {


    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @Value("${jwt.public.key}")
    RSAPublicKey key;

    @Value("${jwt.private.key}")
    RSAPrivateKey privateKey;

    public SecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsServiceImpl);
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtFilter jwtFilter, OAuth2ResourceServerAutoConfiguration oAuth2ResourceServerAutoConfiguration) throws Exception {
//
//        http
//                .authorizeHttpRequests(
//                        (authorize) -> authorize
//                                .requestMatchers("/").anonymous()
////                                .requestMatchers("/login-page").anonymous()
////                                .requestMatchers("/token").anonymous()
////                                .requestMatchers("/r/r_admin").hasRole("admin")
////                                .requestMatchers("/r/r_user").hasRole("user")
////                                .requestMatchers("/r/r_guess").hasRole("guess")
//                                // 其他所有请求都需要身份认证
////                                .anyRequest().authenticated()
//                )
//                .httpBasic(Customizer.withDefaults())
//                .csrf(AbstractHttpConfigurer::disable)
//
////                .csrf((csrf) -> csrf.ignoringRequestMatchers("/"))
////                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
////                .sessionManagement((session) -> session.sessionCreationPolicy(
////                        SessionCreationPolicy.STATELESS
////                ))
////                .exceptionHandling(
////                        (exceptions) -> exceptions.authenticationEntryPoint(
////                                        new BearerTokenAuthenticationEntryPoint())
////                                .accessDeniedHandler(new BearerTokenAccessDeniedHandler())
////                )
//        ;
////        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//        return http.build();
//    }


    @Bean
    JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(this.key).privateKey(this.privateKey).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }
    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(this.key).build();
    }

}
