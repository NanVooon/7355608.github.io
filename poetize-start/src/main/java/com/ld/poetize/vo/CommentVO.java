package com.ld.poetize.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author zuosy
 * @Date 2024/1/25 21:09
 **/
@Data
public class CommentVO {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "评论来源标识")
    private Integer source;

    @Schema(description = "评论来源类型")
    private String type;

    @Schema(description = "点赞数")
    private Integer likeCount;

    @Schema(description = "评论内容")
    private String commentContent;

    @Schema(description = "评论额外信息")
    private String commentInfo;

    @Schema(description = "发表用户")
    private String createBy;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
