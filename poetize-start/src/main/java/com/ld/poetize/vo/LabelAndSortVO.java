package com.ld.poetize.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @Author zuosy
 * @Date 2024/1/25 21:23
 **/
@Data
public class LabelAndSortVO {

    @Schema(description = "分类下拉框")
    private List<Sort> sorts;

    @Schema(description = "标签下拉框")
    private List<Label> labels;

    @Data
    public static class Sort{
        private Long id;

        private String sortName;
    }

    @Data
    public static class Label{
        private Long id;

        private Long sortId;

        private String labelName;
    }
}
