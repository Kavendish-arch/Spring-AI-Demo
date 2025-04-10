package com.chen.securityadmin.config;

import com.chen.securityadmin.filter.JwtFilter;
import com.chen.securityadmin.service.impl.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
* @projectName SpringAIDemo
* @package com.chen.securityadmin.config
* @className com.chen.securityadmin.config.SecurityConfig

* @author chenyingtao
* @date 2025/4/10 16:36
* @version 1.0
* @description @todo 
*/
@Configuration
//@AllArgsConstructor
public class SecurityConfig{

    private final UserDetailsServiceImpl userDetailsServiceImpl;

    public SecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsServiceImpl);
    }

    @Bean
    public SecurityFilterChain  securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(
                (authz) -> authz
                        .requestMatchers("/login-page").anonymous()
                        .requestMatchers("/r/r_admin").hasRole("admin")
                        .requestMatchers("/r/r_user").hasRole("user")
                        .requestMatchers("/r/r_guess").hasRole("guess")
                        // 其他所有请求都需要身份认证
                        .anyRequest().authenticated()

        ).httpBasic(Customizer.withDefaults());
//        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
