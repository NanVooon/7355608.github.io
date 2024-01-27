package com.ld.poetize.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author zuosy
 * @Date 2024/1/27 11:03
 **/
@Data
public class TreeHoleVO {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "内容")
    private String message;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}
