package com.ld.poetize.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author zuosy
 * @Date 2024/1/25 20:57
 **/
@Data
public class LabelVO {

    private Long labelId;

    @Schema(description = "分类ID")
    private Long sortId;

    @Schema(description = "标签名称")
    private String labelName;

    @Schema(description = "标签描述")
    private String labelDescription;

    @Schema(description = "文章总数")
    private Integer countOfLabel;
}
