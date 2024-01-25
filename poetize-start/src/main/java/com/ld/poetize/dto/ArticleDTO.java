package com.ld.poetize.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

/**
 * @Author zuosy
 * @Date 2024/1/25 20:38
 **/
@Data
public class ArticleDTO {

    @Schema(description = "id")
    @Null(message = "id必须为空", groups = {Insert.class})
    @NotNull(message = "id不能为空", groups = {Update.class})
    private Long id;

    @Schema(description = "分类ID")
    @NotNull(message = "分类不能为空", groups = {Insert.class, Update.class})
    private Long sortId;

    @Schema(description = "标签ID")
    @NotNull(message = "标签不能为空", groups = {Insert.class, Update.class})
    private Long labelId;

    @Schema(description = "文章标题")
    @NotNull(message = "文章标题不能为空", groups = {Insert.class, Update.class})
    private String articleTitle;

    @Schema(description = "文章内容")
    @NotNull(message = "文章内容不能为空", groups = {Insert.class, Update.class})
    private String articleContent;

    @Schema(description = "封面")
    @NotNull(message = "封面不能为空", groups = {Insert.class, Update.class})
    private String articleCover;

    @Schema(description = "不可见访问密码")
    private String password;

    @Schema(description = "是否启用评论")
    @NotNull(message = "是否启用评论不能为空", groups = {Insert.class, Update.class})
    private Boolean commentStatus;

    @Schema(description = "是否推荐")
    @NotNull(message = "是否推荐不能为空", groups = {Insert.class, Update.class})
    private Boolean recommendStatus;

    @Schema(description = "是否可见")
    @NotNull(message = "是否可见不能为空", groups = {Insert.class, Update.class})
    private Boolean viewStatus;

    @Schema(description = "提示")
    private String tips;

    @Schema(description = "视频链接")
    private String videoUrl;
}
