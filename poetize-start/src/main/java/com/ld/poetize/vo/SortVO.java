package com.ld.poetize.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @Author zuosy
 * @Date 2024/1/25 20:56
 **/
@Data
public class SortVO {

    private Long sortId;

    @Schema(description = "分类名称")
    private String sortName;

    @Schema(description = "分类描述")
    private String sortDescription;

    @Schema(description = "分类类型")
    private String sortType;

    @Schema(description = "排序")
    private Integer priority;

    @Schema(description = "标签信息")
    private List<LabelVO> labels;

    @Schema(description = "文章总数")
    private Long countOfSort;
}
