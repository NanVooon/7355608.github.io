package com.ld.poetize.handler;

import com.ld.poetize.utils.Oauth2Response;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.server.resource.authentication.AbstractOAuth2TokenAuthenticationToken;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

/**
 * @Author zuosy
 * @Date 2024/1/24 20:03
 **/
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        if(request.getUserPrincipal() instanceof AbstractOAuth2TokenAuthenticationToken){
            //非AuthenticationException、AccessDeniedException异常，则直接响应
            Oauth2Response.oauth2Exception(response, HttpStatus.FORBIDDEN.value(), "权限不足");
        }else {
            Oauth2Response.oauth2Exception(response,500, accessDeniedException.getMessage());
        }
    }

}
