package com.ld.poetize.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @Author zuosy
 * @Date 2024/1/25 21:26
 **/
@Data
public class UserDTO {

    @Schema(description = "id")
    @NotNull(message = "id不能为空", groups = {ChangeUserStatus.class, ChangeUserAdmire.class})
    private Long userId;

    @Schema(description = "状态")
    @NotNull(message = "状态不能为空", groups = {ChangeUserStatus.class})
    private Boolean status;

    @Schema(description = "赞赏")
    @NotNull(message = "状态不能为空", groups = {ChangeUserAdmire.class})
    private String admire;

    public interface ChangeUserStatus{}

    public interface ChangeUserAdmire{}
}
