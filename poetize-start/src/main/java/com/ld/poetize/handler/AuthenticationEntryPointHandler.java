package com.ld.poetize.handler;

import com.ld.poetize.constants.BaseConstant;
import com.ld.poetize.utils.Oauth2Response;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import java.io.IOException;

/**
 * @Author zuosy
 * @Date 2024/1/24 19:59
 **/
public class AuthenticationEntryPointHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        if(authException instanceof InsufficientAuthenticationException){
            String accept = request.getHeader("accept");
            if(accept.contains(MediaType.TEXT_HTML_VALUE)){
                //如果是html请求类型，则返回登录页
                LoginUrlAuthenticationEntryPoint loginUrlAuthenticationEntryPoint = new LoginUrlAuthenticationEntryPoint(BaseConstant.OAuth2.LOGIN_URL);
                loginUrlAuthenticationEntryPoint.commence(request,response,authException);
            }else {
                //如果是api请求类型，则返回json
                Oauth2Response.oauth2Exception(response, 500, "需要带上令牌进行访问");
            }
        }else if(authException instanceof InvalidBearerTokenException){
            Oauth2Response.oauth2Exception(response, 500, "令牌无效或已过期");
        }else{
            Oauth2Response.oauth2Exception(response, 500, authException.getMessage());
        }

    }
}