package com.ld.poetize.utils;

import com.ld.poetize.entity.LoginUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Map;

/**
 * @Author zuosy
 * @Date 2024/1/25 21:31
 **/
public class SecurityUtil {

    /**
     * 获取当前登录用户账号
     * 主要用于资源服务器
     * @return
     */
    public static LoginUser getLoginUser(){
        LoginUser loginUser = new LoginUser();
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        Map<String, Object> tokenAttributes = jwtAuthenticationToken.getTokenAttributes();
        if (tokenAttributes.containsKey("sub")){
            String username = tokenAttributes.get("sub").toString();
            loginUser.setUsername(username);
        }
        return loginUser;
    }
}
