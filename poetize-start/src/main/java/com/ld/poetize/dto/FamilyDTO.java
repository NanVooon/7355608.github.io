package com.ld.poetize.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

/**
 * @Author zuosy
 * @Date 2024/1/27 17:21
 **/
@Data
public class FamilyDTO {

    @Schema(description = "id")
    @Null(message = "id必须为空", groups = {Insert.class})
    @NotNull(message = "id不能为空", groups = {Update.class})
    private Long id;

    @Schema(description = "背景封面")
    private String bgCover;

    @Schema(description = "男生头像")
    private String manCover;

    @Schema(description = "女生头像")
    private String womanCover;

    @Schema(description = "男生昵称")
    private String manName;

    @Schema(description = "女生昵称")
    private String womanName;

    @Schema(description = "计时")
    private String timing;

    @Schema(description = "倒计时标题")
    private String countdownTitle;

    @Schema(description = "倒计时时间")
    private String countdownTime;

    @Schema(description = "是否启用[0:否，1:是]")
    private Boolean status;

    @Schema(description = "额外信息")
    private String familyInfo;

    @Schema(description = "点赞数")
    private Integer likeCount;
}
