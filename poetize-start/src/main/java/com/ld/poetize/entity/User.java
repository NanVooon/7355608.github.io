package com.ld.poetize.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ld.poetize.utils.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author zuosy
 * @Date 2024/1/24 20:08
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 是否启用[0:否，1:是]
     */
    private Boolean userStatus;

    /**
     * 性别[1:男，2:女，0:保密]
     */
    private String gender;

    /**
     * openId
     */
    private String openId;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 赞赏
     */
    private String admire;

    /**
     * 订阅
     */
    private String subscribe;

    /**
     * 简介
     */
    private String introduction;

    /**
     * 用户类型[0:admin，1:管理员，2:普通用户]
     */
    private String userType;
}
