package com.ld.poetize.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author zuosy
 * @Date 2024/1/25 21:22
 **/
@Data
public class WebInfoVO {

    @Schema(description = "id")
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

    @Schema(description = "随机头像")
    private String randomAvatar;

    @Schema(description = "随机名称")
    private String randomName;

    @Schema(description = "随机封面")
    private String randomCover;

    @Schema(description = "看板娘消息")
    private String waifuJson;

    @Schema(description = "是否启用[0:否，1:是]")
    private Boolean status;
}
