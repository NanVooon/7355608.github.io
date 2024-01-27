package com.ld.poetize.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author zuosy
 * @Date 2024/1/25 20:26
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class Article extends BaseEntity {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 分类ID
     */
    private Long sortId;

    /**
     * 标签ID
     */
    private Long labelId;

    /**
     * 封面
     */
    private String articleCover;

    /**
     * 博文标题
     */
    private String articleTitle;

    /**
     * 博文内容
     */
    private String articleContent;

    /**
     * 视频链接
     */
    private String videoUrl;

    /**
     * 密码
     */
    private String password;

    /**
     * 提示
     */
    private String tips;

    /**
     * 是否可见[0:否，1:是]
     */
    private Boolean viewStatus;

    /**
     * 是否加密[0:否，1:是]
     */
    private Boolean encoderStatus;

    /**
     * 浏览量
     */
    private Integer viewCount;

    /**
     * 点赞数
     */
    private Integer likeCount;

    /**
     * 是否启用评论[0:否，1:是]
     */
    private Boolean commentStatus;

    /**
     * 是否推荐[0:否，1:是]
     */
    private Boolean recommendStatus;
}
