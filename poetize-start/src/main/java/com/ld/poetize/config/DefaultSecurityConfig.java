package com.ld.poetize.config;

import com.ld.poetize.handler.AuthenticationEntryPointHandler;
import com.ld.poetize.handler.MyAccessDeniedHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 认证中心默认配置
 *
 * @Author zuosy
 * @Date 2024/1/24 19:58
 **/
@Configuration
@EnableWebSecurity
public class DefaultSecurityConfig {

    /**
     * Spring Security 过滤链配置（此处是纯Spring Security相关配置）
     */
    @Bean
    @Order(2)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
            throws Exception {
        http.authorizeHttpRequests(authorize -> authorize.requestMatchers(
                                "/login",
                                "/v3/api-docs/**",
                                "/doc.html",
                                "/favicon.ico",
                                "/webjars/**"
                        ).permitAll()
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
}
