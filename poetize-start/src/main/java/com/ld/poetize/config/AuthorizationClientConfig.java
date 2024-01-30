package com.ld.poetize.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author zuosy
 * @Date 2023/12/10 19:49
 **/
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true, securedEnabled = true)
public class AuthorizationClientConfig {

    /**
     * 应用服务向认证服务器校验token
     *
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        //不校验的路径
        Set<String> set = new HashSet<>();
        String[] ignoreUrls = set.toArray(new String[0]);

        http.authorizeHttpRequests(authorize ->
                authorize.requestMatchers(ignoreUrls).permitAll()
                        //其他请求，需要认证
                        .anyRequest().authenticated()
        );

        http.oauth2ResourceServer(oauth2 -> oauth2
                .jwt(Customizer.withDefaults())
        );
        return http.build();

    }
}
