package com.ld.poetize.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM:dd HH:mm:ss")
    private LocalDateTime createTime;
}
