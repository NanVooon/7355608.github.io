package com.ld.poetize.dto;

import com.ld.poetize.utils.PageUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author zuosy
 * @Date 2024/1/25 21:09
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class CommentPageDTO extends PageUtil {

    @Schema(description = "评论来源类型")
    private String commentType;

    @Schema(description = "评论来源标识")
    private String source;
}