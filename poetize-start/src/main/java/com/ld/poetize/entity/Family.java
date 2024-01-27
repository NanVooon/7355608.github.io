package com.ld.poetize.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author zuosy
 * @Date 2024/1/27 17:12
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class Family extends BaseEntity {


    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 背景封面
     */
    private String bgCover;

    /**
     * 男生头像
     */
    private String manCover;

    /**
     * 女生头像
     */
    private String womanCover;

    /**
     * 男生昵称
     */
    private String manName;

    /**
     * 女生昵称
     */
    private String womanName;

    /**
     * 计时
     */
    private String timing;

    /**
     * 倒计时标题
     */
    private String countdownTitle;

    /**
     * 倒计时时间
     */
    private String countdownTime;

    /**
     * 是否启用[0:否，1:是]
     */
    private Boolean status;

    /**
     * 额外信息
     */
    private String familyInfo;

    /**
     * 点赞数
     */
    private Integer likeCount;
}
