package com.ld.poetize.authentication.password;

import com.ld.poetize.utils.BaseConstant;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationGrantAuthenticationToken;

import java.util.Map;

/**
 * @Author zuosy
 * @Date 2024/1/24 20:12
 **/
public class PasswordToken extends OAuth2AuthorizationGrantAuthenticationToken {

    public PasswordToken(Authentication clientPrincipal,
                         @Nullable Map<String, Object> additionalParameters) {
        super(new AuthorizationGrantType(BaseConstant.OAuth2.GRANT_TYPE_PASSWORD),
                clientPrincipal, additionalParameters);
    }
}
