package com.ld.poetize.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ld.poetize.dto.UserDTO;
import com.ld.poetize.dto.UserPageDTO;
import com.ld.poetize.entity.LoginUser;
import com.ld.poetize.entity.User;
import com.ld.poetize.mapper.UserMapper;
import com.ld.poetize.service.UserService;
import com.ld.poetize.utils.SecurityUtil;
import com.ld.poetize.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @Author zuosy
 * @Date 2024/1/24 20:15
 **/
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final PasswordEncoder passwordEncoder;

    @Override
    public Page<UserVO> listPage(UserPageDTO userDTO) {
        Page<User> reqPage = Page.of(userDTO.getCurrent(), userDTO.getSize());
        Page<User> userPage = baseMapper.selectPage(reqPage, new LambdaQueryWrapper<User>()
                .eq(Objects.nonNull(userDTO.getUserStatus()), User::getUserStatus, userDTO.getUserStatus())
                .eq(StringUtils.hasText(userDTO.getUserType()), User::getUserType, userDTO.getUserType())
                .eq(StringUtils.hasText(userDTO.getSearch()), User::getUsername, userDTO.getSearch())
                .or()
                .eq(StringUtils.hasText(userDTO.getSearch()), User::getPhoneNumber, userDTO.getSearch()));

        Page<UserVO> result = new Page<>();
        if (CollectionUtils.isEmpty(userPage.getRecords())){
            result.setTotal(0L);
            result.setRecords(new ArrayList<>());
            return result;
        }
        result.setTotal(userPage.getTotal());
        result.setRecords(BeanUtil.copyToList(userPage.getRecords(), UserVO.class));
        return result;
    }

    @Override
    public Boolean changeUserStatus(UserDTO userDTO) {
        int update = baseMapper.update(new LambdaUpdateWrapper<User>()
                .eq(User::getId, userDTO.getUserId())
                .set(User::getUserStatus, userDTO.getStatus()));
        return update > 0;
    }

    @Override
    public Boolean changeUserAdmire(UserDTO userDTO) {
        int update = baseMapper.update(new LambdaUpdateWrapper<User>()
                .eq(User::getId, userDTO.getUserId())
                .set(User::getAdmire, userDTO.getAdmire()));
        return update > 0;
    }

    @Override
    public Boolean deleteUser(Long id) {
        return baseMapper.deleteById(id) > 0;
    }

    @Override
    public UserVO getUserInfo() {
        LoginUser loginUser = SecurityUtil.getLoginUser();
        String username = loginUser.getUsername();
        if (Objects.isNull(username)){
            throw new IllegalArgumentException("系统异常");
        }
        User user = baseMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, username));
        if (Objects.isNull(user)){
            throw new IllegalArgumentException("系统异常");
        }
        return BeanUtil.copyProperties(user, UserVO.class);
    }
}
