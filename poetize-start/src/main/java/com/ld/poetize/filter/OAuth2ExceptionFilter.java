package com.ld.poetize.filter;

import com.ld.poetize.utils.Oauth2Response;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @Author zuosy
 * @Date 2024/1/24 20:16
 **/
public class OAuth2ExceptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        }catch (Exception e) {
            if (e instanceof AuthenticationException || e instanceof AccessDeniedException) {
                throw e;
            }
            //非AuthenticationException、AccessDeniedException异常，则直接响应
            Oauth2Response.oauth2Exception(response, 500, "认证系统异常");
        }

    }
}
