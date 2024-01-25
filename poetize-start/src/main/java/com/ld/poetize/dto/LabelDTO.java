package com.ld.poetize.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

/**
 * @Author zuosy
 * @Date 2024/1/25 21:03
 **/
@Data
public class LabelDTO {

    @Schema(description = "id")
    @Null(message = "id必须为空", groups = {Insert.class})
    @NotNull(message = "id不能为空", groups = {Update.class})
    private Long id;

    @Schema(description = "分类id")
    private Long sortId;

    @Schema(description = "标签名称")
    private String labelName;

    @Schema(description = "标签")
    private String labelDescription;
}
