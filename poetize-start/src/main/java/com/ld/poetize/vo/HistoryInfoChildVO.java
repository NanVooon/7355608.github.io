package com.ld.poetize.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author zuosy
 * @Date 2024/1/27 10:13
 **/
@Data
public class HistoryInfoChildVO {

    @Schema(description = "key")
    private String key;

    @Schema(description = "value")
    private String value;
}
