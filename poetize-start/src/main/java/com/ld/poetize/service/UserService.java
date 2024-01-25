package com.ld.poetize.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ld.poetize.dto.UserDTO;
import com.ld.poetize.dto.UserPageDTO;
import com.ld.poetize.entity.User;
import com.ld.poetize.vo.UserVO;

/**
 * @Author zuosy
 * @Date 2024/1/24 20:07
 **/
public interface UserService extends IService<User> {

    /**
     * 用户列表
     * @param userDTO
     * @return
     */
    Page<UserVO> listPage(UserPageDTO userDTO);

    /**
     * 修改用户状态
     * @param userDTO
     * @return
     */
    Boolean changeUserStatus(UserDTO userDTO);

    /**
     * 修改用户赞赏
     * @param userDTO
     * @return
     */
    Boolean changeUserAdmire(UserDTO userDTO);

    /**
     * 获取当前用户详情
     * @return
     */
    UserVO getUserInfo();
}
