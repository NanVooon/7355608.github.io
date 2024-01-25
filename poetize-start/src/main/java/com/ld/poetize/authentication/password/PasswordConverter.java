package com.ld.poetize.authentication.password;

import com.ld.poetize.constants.BaseConstant;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.web.authentication.AuthenticationConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author zuosy
 * @Date 2024/1/24 20:11
 **/
public class PasswordConverter implements AuthenticationConverter {

    @Nullable
    @Override
    public Authentication convert(HttpServletRequest request) {
        // grant_type (REQUIRED)
        String grantType = request.getParameter(OAuth2ParameterNames.GRANT_TYPE);
        if (!BaseConstant.OAuth2.GRANT_TYPE_PASSWORD.equals(grantType)) {
            return null;
        }

        Map<String, String[]> parameterMap = request.getParameterMap();
        if (!parameterMap.containsKey(OAuth2ParameterNames.USERNAME)){
            throw new IllegalArgumentException("无效请求，用户名不能为空！");
        }
        if (!parameterMap.containsKey(OAuth2ParameterNames.PASSWORD)){
            throw new IllegalArgumentException("无效请求，密码不能为空！");
        }
        //收集要传入PasswordGrantAuthenticationToken构造方法的参数，
        //该参数接下来在PasswordGrantAuthenticationProvider中使用
        Map<String, Object> additionalParameters = new HashMap<>();
        parameterMap.forEach((key, value) -> {
            if (!key.equals(OAuth2ParameterNames.GRANT_TYPE) &&
                    !key.equals(OAuth2ParameterNames.CLIENT_ID) &&
                    !key.equals(OAuth2ParameterNames.CODE)) {
                for (String v : value) {
                    additionalParameters.put(key, v);
                }
            }
        });
        Authentication clientPrincipal = SecurityContextHolder.getContext().getAuthentication();
        //返回自定义的PasswordGrantAuthenticationToken对象
        return new PasswordToken(clientPrincipal, additionalParameters);
    }
}
