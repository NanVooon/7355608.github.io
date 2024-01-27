package com.ld.poetize.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ld.poetize.dto.UserDTO;
import com.ld.poetize.dto.UserPageDTO;
import com.ld.poetize.service.UserService;
import com.ld.poetize.utils.web.R;
import com.ld.poetize.vo.UserVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author zuosy
 * @Date 2024/1/24 21:15
 **/
@Tag(name = "用户信息")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/list")
    @Operation(summary = "用户列表")
    @PreAuthorize("hasAuthority('SCOPE_administrator')")
    public R<Page<UserVO>> list(UserPageDTO userDTO){
        return R.okForData(userService.listPage(userDTO));
    }

    @PostMapping("/changeUserStatus")
    @Operation(summary = "修改用户状态")
    @PreAuthorize("hasAuthority('SCOPE_administrator')")
    public R<Boolean> changeUserStatus(@RequestBody @Validated(UserDTO.ChangeUserStatus.class) UserDTO userDTO){
        return R.okForData(userService.changeUserStatus(userDTO));
    }

    @PostMapping("/changeUserAdmire")
    @Operation(summary = "修改用户赞赏")
    @PreAuthorize("hasAuthority('SCOPE_administrator')")
    public R<Boolean> changeUserAdmire(@RequestBody @Validated(UserDTO.ChangeUserAdmire.class) UserDTO userDTO){
        return R.okForData(userService.changeUserAdmire(userDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除用户")
    @PreAuthorize("hasAuthority('SCOPE_administrator')")
    public R<Boolean> deleteUser(@PathVariable("id") Long id){
        return R.okForData(userService.deleteUser(id));
    }

    @GetMapping("/getUserInfo")
    @Operation(summary = "获取当前用户详情")
    public R<UserVO> getUserInfo(){
        return R.okForData(userService.getUserInfo());
    }
}
