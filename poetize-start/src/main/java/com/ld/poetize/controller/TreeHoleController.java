package com.ld.poetize.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ld.poetize.dto.TreeHolePageDTO;
import com.ld.poetize.service.TreeHoleService;
import com.ld.poetize.utils.web.R;
import com.ld.poetize.vo.TreeHoleVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author zuosy
 * @Date 2024/1/25 20:36
 **/
@Tag(name = "树洞信息")
@RestController
@RequestMapping("/tree/hole")
@RequiredArgsConstructor
public class TreeHoleController {

    private final TreeHoleService treeHoleService;

    @GetMapping("/pageList")
    @Operation(summary = "分页数据")
    @PreAuthorize("hasAuthority('SCOPE_administrator')")
    public R<Page<TreeHoleVO>> pageList(@Validated TreeHolePageDTO treeHolePageDTO){
        return R.okForData(treeHoleService.pageList(treeHolePageDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除信息")
    @PreAuthorize("hasAuthority('SCOPE_administrator')")
    public R<Boolean> deleteTreeHole(@PathVariable("id") Long id){
        return R.okForData(treeHoleService.deleteTreeHole(id));
    }
}
