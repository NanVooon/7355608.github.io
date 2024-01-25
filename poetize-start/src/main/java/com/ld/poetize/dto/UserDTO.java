package com.ld.poetize.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author zuosy
 * @Date 2024/1/25 21:26
 **/
@Data
public class UserDTO {

    @Schema(description = "id")
    private Long userId;

    @Schema(description = "状态")
    private Boolean flag;

    @Schema(description = "赞赏")
    private String admire;
}
