package com.ld.poetize.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

/**
 * @Author zuosy
 * @Date 2024/1/25 20:57
 **/
@Data
public class SortDTO {

    @Schema(description = "id")
    @Null(message = "id必须为空", groups = {Insert.class})
    @NotNull(message = "id不能为空", groups = {Update.class})
    private Long id;

    @Schema(description = "分类名称")
    @NotNull(message = "分类名称不能为空", groups = {Insert.class, Update.class})
    private String sortName;

    @Schema(description = "分类描述")
    @NotNull(message = "分类描述不能为空", groups = {Insert.class, Update.class})
    private String sortDescription;

    @Schema(description = "分类类型[0:导航栏分类，1:普通分类]")
    @NotNull(message = "分类类型不能为空", groups = {Insert.class, Update.class})
    private String sortType;

    @NotNull(message = "分类优先级不能为空", groups = {Insert.class, Update.class})
    @Schema(description = "排序")
    private String priority;
}
