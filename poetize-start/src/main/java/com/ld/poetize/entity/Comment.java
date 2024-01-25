package com.ld.poetize.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author zuosy
 * @Date 2024/1/25 21:07
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class Comment extends BaseEntity {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 评论来源标识
     */
    private Integer source;

    /**
     * 父评论ID
     */
    private Long parentCommentId;

    /**
     * 评论来源类型
     */
    private String type;

    /**
     * 父发表用户名ID
     */
    private Integer parentUserId;

    /**
     * 点赞数
     */
    private Integer likeCount;

    /**
     * 评论内容
     */
    private String commentContent;

    /**
     * 评论额外信息
     */
    private String commentInfo;

    /**
     * 楼层评论ID
     */
    private Long floorCommentId;
}