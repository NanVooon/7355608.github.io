package com.ld.poetize.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author zuosy
 * @Date 2024/1/27 16:36
 **/
@Data
public class ResourceVO {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "名称")
    private String originalName;

    @Schema(description = "资源类型[0:背景图,1:表情包]")
    private String type;

    @Schema(description = "路径")
    private String path;

    @Schema(description = "文件大小")
    private Integer size;

    @Schema(description = "存储平台")
    private String storeType;

    @Schema(description = "")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
