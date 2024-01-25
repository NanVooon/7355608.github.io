package com.ld.poetize.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author zuosy
 * @Date 2024/1/25 20:36
 **/
@Data
public class ArticleVO {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "作者")
    private String createBy;

    @Schema(description = "标题")
    private String articleTitle;

    @Schema(description = "分类")
    private String sortName;

    @Schema(description = "标签")
    private String labelName;

    @Schema(description = "浏览量")
    private Integer viewCount;

    @Schema(description = "点赞数")
    private Integer likeCount;

    @Schema(description = "是否可见")
    private Boolean viewStatus;

    @Schema(description = "封面")
    private String articleCover;

    @Schema(description = "是否启用评论")
    private Boolean commentStatus;

    @Schema(description = "是否推荐")
    private Boolean recommendStatus;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM:dd HH:mm:ss")
    private LocalDateTime createTime;

    @Schema(description = "修改时间")
    @JsonFormat(pattern = "yyyy-MM:dd HH:mm:ss")
    private LocalDateTime updateTime;
    /*列表数据字段end*/


    @Schema(description = "内容")
    private String articleContent;

    @Schema(description = "分类ID")
    private Long sortId;

    @Schema(description = "标签ID")
    private Long labelId;

    @Schema(description = "视频链接")
    private String videoUrl;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "提示")
    private String tips;
}
