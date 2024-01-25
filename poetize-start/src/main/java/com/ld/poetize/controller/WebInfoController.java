package com.ld.poetize.controller;

import cn.hutool.core.bean.BeanUtil;
import com.ld.poetize.dto.WebInfoDTO;
import com.ld.poetize.entity.WebInfo;
import com.ld.poetize.service.WebInfoService;
import com.ld.poetize.utils.web.R;
import com.ld.poetize.vo.LabelAndSortVO;
import com.ld.poetize.vo.WebInfoVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Update;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author zuosy
 * @Date 2024/1/25 21:18
 **/
@Tag(name = "网站信息")
@RestController
@RequestMapping("/webInfo")
@RequiredArgsConstructor
public class WebInfoController {

    private final WebInfoService webInfoService;

    @GetMapping("/getWebInfo")
    @Operation(summary = "获取网站信息")
    @PreAuthorize("hasAuthority('SCOPE_administrator')")
    public R<WebInfoVO> getWebInfo(){
        return R.okForData(webInfoService.getWebInfo());
    }

    @PostMapping("/updateWebInfo")
    @Operation(summary = "更新网站信息")
    @PreAuthorize("hasAuthority('SCOPE_administrator')")
    public R<Boolean> updateWebInfo(@RequestBody @Validated(Update.class) WebInfoDTO webInfoDTO){
        return R.okForData(webInfoService.updateById(BeanUtil.copyProperties(webInfoDTO, WebInfo.class)));
    }

    @GetMapping("/listSortAndLabel")
    @Operation(summary = "获取分类和标签下拉框")
    @PreAuthorize("hasAuthority('SCOPE_administrator')")
    public R<LabelAndSortVO> listSortAndLabel(){
        return R.okForData(webInfoService.listSortAndLabel());
    }
}
