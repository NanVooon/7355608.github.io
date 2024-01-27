package com.ld.poetize.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.apache.ibatis.annotations.Update;

/**
 * @Author zuosy
 * @Date 2024/1/25 21:22
 **/
@Data
public class WebInfoDTO {

    @Schema(description = "id")
    @NotNull(message = "id不能为空", groups = {Update.class})
    private Long id;

    @Schema(description = "网站名称")
    private String webName;

    @Schema(description = "网站信息")
    private String webTitle;

    @Schema(description = "公告")
    private String notices;

    @Schema(description = "页脚")
    private String footer;

    @Schema(description = "背景")
    private String backgroundImage;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "看板娘消息")
    private String waifuJson;

    @Schema(description = "是否启用[0:否，1:是]")
    private Boolean status;
}
