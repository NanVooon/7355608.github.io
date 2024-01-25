package com.ld.poetize.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ld.poetize.mapper.UserMapper;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @Author zuosy
 * @Date 2024/1/24 20:22
 **/
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.ld.poetize.entity.User userEntity = userMapper.selectOne(new LambdaQueryWrapper<com.ld.poetize.entity.User>().eq(com.ld.poetize.entity.User::getUsername, username));
        if (Objects.isNull(userEntity)){
            throw new UsernameNotFoundException("用户不存在");
        }

        return User.withUsername(userEntity.getUsername()).password(userEntity.getPassword()).authorities(convertAuthor(userEntity.getUserType())).build();
    }

    private String convertAuthor(String type){
        return switch (type) {
            case "0" -> "administrator";
            case "1" -> "admin";
            case "2" -> "common";
            default -> "";
        };
    }

}
