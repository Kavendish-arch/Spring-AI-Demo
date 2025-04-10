package com.chen.securityadmin.filter;

import com.chen.securityadmin.tool.JwtProperties;
import com.chen.securityadmin.tool.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author chenyingtao
 * @version 1.0
 * @projectName SpringAIDemo
 * @package com.chen.securityadmin.filter
 * @className com.chen.securityadmin.filter.JwtFilter
 * @date 2025/4/10 22:00
 * @description @todo
 */
//@Component
public class JwtFilter extends OncePerRequestFilter {

//    @Autowired
    private JwtProperties jwtProperties;

//    @Autowired
    private UserDetailsService userDetailsService;

//    @Autowired
    private JwtUtil jwtUtil;

    /**
     * JWT认证过滤器核心处理方法
     * @param request HTTP请求对象，用于获取请求头及请求内容
     * @param response HTTP响应对象，用于返回认证错误信息
     * @param filterChain 过滤器链，用于继续处理后续过滤流程
     * @throws ServletException 当处理请求时发生servlet相关异常
     * @throws IOException 当输入输出异常发生时抛出
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException, IOException {
        // 从请求头获取JWT令牌
        String token = request.getHeader(jwtProperties.getTokenName());

        if (token != null) {
            try {
                // 解析JWT令牌获取声明信息
                Claims claims = jwtUtil.parseJWT(token);
                String username = (String) claims.get("userName");

                // 验证用户身份并设置安全上下文
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    // 加载用户详细信息
                    UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                    // 校验令牌有效性
                    if (jwtUtil.isTokenValid(token, userDetails)) {
                        // 创建认证令牌并存入安全上下文
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            } catch (ExpiredJwtException ex) {
                // 处理令牌过期异常
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Token has expired.");
            } catch (JwtException ex) {
                // 处理无效令牌异常
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid token.");
            }
        } else {
            // 处理缺失令牌的情况
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "No token provided.");
        }
        // 继续执行后续过滤器链
        filterChain.doFilter(request, response);
    }

}