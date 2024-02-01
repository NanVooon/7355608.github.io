package com.ld.poetize.config;

import com.ld.poetize.handler.AuthenticationEntryPointHandler;
import com.ld.poetize.handler.MyAccessDeniedHandler;
import jakarta.annotation.security.PermitAll;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.HashSet;
import java.util.Set;

/**
 * 认证中心默认配置
 *
 * @Author zuosy
 * @Date 2024/1/24 19:58
 **/
@Configuration
@EnableWebSecurity
public class DefaultSecurityConfig {

    private final RequestMappingHandlerMapping requestMappingHandlerMapping;

    public DefaultSecurityConfig(RequestMappingHandlerMapping requestMappingHandlerMapping) {
        this.requestMappingHandlerMapping = requestMappingHandlerMapping;
    }

    /**
     * Spring Security 过滤链配置（此处是纯Spring Security相关配置）
     */
    @Bean
    @Order(2)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
            throws Exception {
        //白名单及被@Permitall注解修饰的接口地址
        Set<String> ignoreUrl = getUrlsFormPermitAllAnnotation();
        ignoreUrl.add("/login");
        ignoreUrl.add("/v3/api-docs/**");
        ignoreUrl.add("/doc.html");
        ignoreUrl.add("/favicon.ico");
        ignoreUrl.add("/webjars/**");
        String[] array = ignoreUrl.toArray(new String[0]);

        http.authorizeHttpRequests(authorize -> authorize.requestMatchers(array).permitAll()
                        .anyRequest().authenticated())
                .formLogin(formLogin -> formLogin.loginPage("/login"));
        http.oauth2ResourceServer((resourceServer) -> resourceServer
                .jwt(Customizer.withDefaults())
                .authenticationEntryPoint(new AuthenticationEntryPointHandler())
                .accessDeniedHandler(new MyAccessDeniedHandler()));

        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(AbstractHttpConfigurer::disable);
        http.headers(AbstractHttpConfigurer::disable);
        return http.build();
    }

    /**
     * 获取被PermitAll注解修饰的url地址
     */
    private Set<String> getUrlsFormPermitAllAnnotation() {
        Set<String> result = new HashSet<>();
        requestMappingHandlerMapping.getHandlerMethods().forEach((key, method) -> {
            if (method.hasMethodAnnotation(PermitAll.class)) {
                Set<String> urls = key.getPatternValues();
                if (!CollectionUtils.isEmpty(urls)) {
                    result.addAll(urls);
                }
            }
        });
        return result;
    }
}
