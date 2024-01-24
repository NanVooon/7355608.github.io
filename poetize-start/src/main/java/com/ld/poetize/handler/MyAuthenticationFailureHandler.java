package com.ld.poetize.handler;

import com.ld.poetize.utils.Oauth2Response;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * @Author zuosy
 * @Date 2024/1/24 20:21
 **/
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String message = exception.getMessage();
        if (StringUtils.hasText(message)){
            Oauth2Response.oauth2Exception(response, 500, exception.getMessage());
        }else {
            if (exception instanceof OAuth2AuthenticationException){
                Oauth2Response.oauth2Exception(response, 500 , ((OAuth2AuthenticationException) exception).getError().getErrorCode());
            }
        }
    }
}
