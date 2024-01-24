package com.ld.poetize.config;

import com.ld.poetize.authentication.password.PasswordConverter;
import com.ld.poetize.authentication.password.PasswordProvider;
import com.ld.poetize.filter.OAuth2ExceptionFilter;
import com.ld.poetize.handler.AuthenticationEntryPointHandler;
import com.ld.poetize.handler.MyAccessDeniedHandler;
import com.ld.poetize.utils.BaseConstant;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.apache.catalina.util.StandardSessionIdGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;
import org.springframework.security.oauth2.core.oidc.endpoint.OidcParameterNames;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.authorization.*;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.*;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 认证中心核心配置
 *
 * @Author zuosy
 * @Date 2024/1/24 20:07
 **/
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String CUSTOM_CONSENT_PAGE_URI = "/oauth2/consent";

    @Value("${spring.security.oauth2.resource-server.jwt.issuer-uri}")
    private String issuer;


    /**
     * Spring Authorization Server 相关配置
     * 此处方法与下面defaultSecurityFilterChain都是SecurityFilterChain配置，配置的内容有点区别，
     * 因为Spring Authorization Server是建立在Spring Security 基础上的，defaultSecurityFilterChain方法主要
     * 配置Spring Security相关的东西，而此处authorizationServerSecurityFilterChain方法主要配置OAuth 2.1和OpenID Connect 1.0相关的东西
     */
    @Bean
    @Order(1)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http, RegisteredClientRepository registeredClientRepository,
                                                                      AuthorizationServerSettings authorizationServerSettings,
                                                                      OAuth2AuthorizationService authorizationService,
                                                                      OAuth2TokenGenerator<?> tokenGenerator)
            throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                .authorizationEndpoint(authorizationEndpoint ->
                        authorizationEndpoint.consentPage(CUSTOM_CONSENT_PAGE_URI))
                //设置自定义密码模式
                .tokenEndpoint(tokenEndpoint ->
                        tokenEndpoint
                                .accessTokenRequestConverter(
                                        new PasswordConverter())
                                .authenticationProvider(
                                        new PasswordProvider(
                                                authorizationService, tokenGenerator)));

        //设置登录地址，需要进行认证的请求被重定向到该地址
        http.addFilterBefore(new OAuth2ExceptionFilter(), ExceptionTranslationFilter.class)
                .exceptionHandling((exceptions) -> exceptions
                        .defaultAuthenticationEntryPointFor(
                                new LoginUrlAuthenticationEntryPoint(BaseConstant.OAuth2.LOGIN_URL),
                                new MediaTypeRequestMatcher(MediaType.TEXT_HTML)
                        )
                        .authenticationEntryPoint(new AuthenticationEntryPointHandler())
                        .accessDeniedHandler(new MyAccessDeniedHandler())
                )
                .oauth2ResourceServer(oauth2ResourceServer ->
                        oauth2ResourceServer.jwt(Customizer.withDefaults()));

        return  http.build();
    }


    /**
     * 客户端信息
     * 对应表：oauth2_registered_client
     */
    @Bean
    public RegisteredClientRepository registeredClientRepository(JdbcTemplate jdbcTemplate) {
        return new JdbcRegisteredClientRepository(jdbcTemplate);
    }


    /**
     * 授权信息
     * 对应表：oauth2_authorization
     */
    @Bean
    public OAuth2AuthorizationService authorizationService(JdbcTemplate jdbcTemplate, RegisteredClientRepository registeredClientRepository) {
        return new JdbcOAuth2AuthorizationService(jdbcTemplate, registeredClientRepository);
    }

    /**
     * 授权确认
     *对应表：oauth2_authorization_consent
     */
    @Bean
    public OAuth2AuthorizationConsentService authorizationConsentService(JdbcTemplate jdbcTemplate, RegisteredClientRepository registeredClientRepository) {
        return new JdbcOAuth2AuthorizationConsentService(jdbcTemplate, registeredClientRepository);
    }




    /**
     *配置 JWK，为JWT(id_token)提供加密密钥，用于加密/解密或签名/验签
     * JWK详细见：https://datatracker.ietf.org/doc/html/draft-ietf-jose-json-web-key-41
     */
    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        KeyPair keyPair = generateRsaKey();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAKey rsaKey = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return new ImmutableJWKSet<>(jwkSet);
    }

    /**
     *生成RSA密钥对，给上面jwkSource() 方法的提供密钥对
     */
    private static KeyPair generateRsaKey() {
        KeyPair keyPair;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
        }
        catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
        return keyPair;
    }

    /**
     * 配置jwt解析器
     */
    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }

    /**
     *配置认证服务器请求地址
     */
    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        AuthorizationServerSettings.Builder authorizationServerSettingsBuilder = AuthorizationServerSettings.builder();
        //什么都不配置，则使用默认地址
        authorizationServerSettingsBuilder.issuer(issuer);
        return authorizationServerSettingsBuilder.build();
    }

    /**
     *配置token生成器
     */
    @Bean
    OAuth2TokenGenerator<?> tokenGenerator(JWKSource<SecurityContext> jwkSource) {
        JwtGenerator jwtGenerator = new JwtGenerator(new NimbusJwtEncoder(jwkSource));
        jwtGenerator.setJwtCustomizer(jwtCustomizer());
        OAuth2AccessTokenGenerator accessTokenGenerator = new OAuth2AccessTokenGenerator();
        OAuth2RefreshTokenGenerator refreshTokenGenerator = new OAuth2RefreshTokenGenerator();
        return new DelegatingOAuth2TokenGenerator(
                jwtGenerator, accessTokenGenerator, refreshTokenGenerator);
    }

    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> jwtCustomizer() {

        return context -> {
            JwtClaimsSet.Builder claims = context.getClaims();
            if (context.getTokenType().equals(OAuth2TokenType.ACCESS_TOKEN)) {
                //客户端模式不参与用户权限信息处理
                if(!AuthorizationGrantType.CLIENT_CREDENTIALS.equals(context.getAuthorizationGrantType())){
                    // Customize headers/claims for access_token
                    claims.claims(claimsConsumer->{
                        claimsConsumer.merge("scope",context.getPrincipal().getAuthorities(),(scope,authorities)->{
                            Set<String> scopeSet = (Set<String>)scope;
                            Set<String> cloneSet = scopeSet.stream().map(String::new).collect(Collectors.toSet());
                            Collection<SimpleGrantedAuthority> simpleGrantedAuthorities = (Collection<SimpleGrantedAuthority>)authorities;
                            simpleGrantedAuthorities.forEach(simpleGrantedAuthority -> cloneSet.add(simpleGrantedAuthority.getAuthority()));
                            return cloneSet;
                        });
                    });
                }
            } else if (context.getTokenType().getValue().equals(OidcParameterNames.ID_TOKEN)) {
                // Customize headers/claims for id_token
                claims.claim(IdTokenClaimNames.AUTH_TIME, Date.from(Instant.now()));
                StandardSessionIdGenerator standardSessionIdGenerator = new StandardSessionIdGenerator();
                claims.claim("sid", standardSessionIdGenerator.generateSessionId());
            }
        };
    }
}
