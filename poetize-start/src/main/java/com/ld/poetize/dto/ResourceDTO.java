package com.ld.poetize.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;

/**
 * @Author zuosy
 * @Date 2024/1/27 16:42
 **/
@Data
public class ResourceDTO {

    @Schema(description = "id")
    @Null(message = "id必须为空", groups = {Insert.class})
    @NotNull(message = "id不能为空", groups = {Update.class})
    private Long id;

    @Schema(description = "资源类型")
    @NotNull(message = "资源类型不能为空", groups = {Insert.class, Update.class})
    private String type;

    @Schema(description = "id")
    @NotNull(message = "资源路径不能为空", groups = {Insert.class, Update.class})
    private String path;

    @Schema(description = "id")
    @NotNull(message = "文件大小不能为空", groups = {Insert.class, Update.class})
    private Integer size;

    @Schema(description = "id")
    @NotNull(message = "文件名称不能为空", groups = {Insert.class, Update.class})
    private String originalName;

    @Schema(description = "id")
    @NotNull(message = "文件类型不能为空", groups = {Insert.class, Update.class})
    private String mimeType;

    @Schema(description = "id")
    @NotNull(message = "存储平台不能为空", groups = {Insert.class, Update.class})
    private String storeType;

    @Schema(description = "id")
    private LocalDateTime createTime;
}
