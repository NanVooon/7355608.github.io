package com.ld.poetize.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ld.poetize.entity.User;
import com.ld.poetize.mapper.UserMapper;
import com.ld.poetize.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Author zuosy
 * @Date 2024/1/24 20:15
 **/
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
