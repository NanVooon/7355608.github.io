package com.ld.poetize.utils;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author zuosy
 * @Date 2024/1/25 19:14
 **/
@Data
public class PageUtil {

    @Schema(description = "当前页码")
    private Long current;

    @Schema(description = "每页显示数")
    private Long size;
}
