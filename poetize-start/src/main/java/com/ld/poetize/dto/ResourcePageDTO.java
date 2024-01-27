package com.ld.poetize.dto;

import com.ld.poetize.utils.PageUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author zuosy
 * @Date 2024/1/27 16:34
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class ResourcePageDTO extends PageUtil {

    @Schema(description = "资源类型")
    private String resourceType;
}
