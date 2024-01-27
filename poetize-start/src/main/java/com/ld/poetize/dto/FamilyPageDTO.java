package com.ld.poetize.dto;

import com.ld.poetize.utils.PageUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author zuosy
 * @Date 2024/1/27 17:17
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class FamilyPageDTO extends PageUtil {

    @Schema(description = "状态")
    private Boolean status;
}
