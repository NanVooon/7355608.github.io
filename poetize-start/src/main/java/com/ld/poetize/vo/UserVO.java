package com.ld.poetize.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author zuosy
 * @Date 2024/1/25 21:26
 **/
@Data
public class UserVO {

    @Schema(description = "ID")
    private Integer id;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "手机号")
    private String phoneNumber;

    @Schema(description = "用户邮箱")
    private String email;

    @Schema(description = "赞赏")
    private String admire;

    @Schema(description = "用户状态")
    private Boolean userStatus;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "性别")
    private String gender;

    @Schema(description = "简介")
    private String introduction;

    @Schema(description = "用户类型")
    private String userType;

    @Schema(description = "注册时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
