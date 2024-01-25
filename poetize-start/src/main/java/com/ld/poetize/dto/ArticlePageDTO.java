package com.ld.poetize.dto;

import com.ld.poetize.utils.PageUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author zuosy
 * @Date 2024/1/25 20:37
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class ArticlePageDTO extends PageUtil {

    @Schema(description = "分类id")
    private Long sortId;

    @Schema(description = "标签id")
    private Long labelId;

    @Schema(description = "是否推荐")
    private Boolean recommendStatus;

    @Schema(description = "文章标题")
    private String articleTitle;

    @Schema(description = "首页搜索框")
    private String articleSearch;
}
