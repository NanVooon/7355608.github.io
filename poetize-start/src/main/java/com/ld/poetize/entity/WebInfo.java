package com.ld.poetize.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @Author zuosy
 * @Date 2024/1/25 21:20
 **/
@Data
public class WebInfo {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 网站名称
     */
    private String webName;

    /**
     * 网站信息
     */
    private String webTitle;

    /**
     * 公告
     */
    private String notices;

    /**
     * 页脚
     */
    private String footer;

    /**
     * 背景
     */
    private String backgroundImage;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 随机头像
     */
    private String randomAvatar;

    /**
     * 随机名称
     */
    private String randomName;

    /**
     * 随机封面
     */
    private String randomCover;

    /**
     * 看板娘消息
     */
    private String waifuJson;

    /**
     * 是否启用[0:否，1:是]
     */
    private Boolean status;
}
