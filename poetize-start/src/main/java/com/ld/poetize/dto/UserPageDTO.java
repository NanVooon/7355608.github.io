package com.ld.poetize.dto;

import com.ld.poetize.utils.PageUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author zuosy
 * @Date 2024/1/25 21:26
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class UserPageDTO extends PageUtil {

    @Schema(description = "用户状态")
    private Boolean userStatus;

    @Schema(description = "用户类型")
    private String userType;

    @Schema(description = "搜索条件")
    private String search;
}
