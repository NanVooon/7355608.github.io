package com.ld.poetize.controller;

import com.ld.poetize.dto.SortDTO;
import com.ld.poetize.service.SortService;
import com.ld.poetize.utils.web.R;
import com.ld.poetize.vo.SortVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author zuosy
 * @Date 2024/1/25 20:56
 **/
@Tag(name = "分类信息")
@RestController
@RequestMapping("/sort")
@RequiredArgsConstructor
public class SortController {

    private final SortService sortService;

    @GetMapping("/allSortList")
    @Operation(summary = "获取所有分类信息")
    @PreAuthorize("hasAuthority('SCOPE_administrator')")
    public R<List<SortVO>> allSortList(){
        return R.okForData(sortService.allSortList());
    }

    @PostMapping("/saveSort")
    @Operation(summary = "保存分类信息")
    @PreAuthorize("hasAuthority('SCOPE_administrator')")
    public R<Boolean> saveSort(@RequestBody @Validated(Insert.class) SortDTO sortDTO){
        return R.okForData(sortService.saveSort(sortDTO));
    }

    @PostMapping("/updateSort")
    @Operation(summary = "修改分类信息")
    @PreAuthorize("hasAuthority('SCOPE_administrator')")
    public R<Boolean> updateSort(@RequestBody @Validated(Update.class)SortDTO sortDTO){
        return R.okForData(sortService.updateSort(sortDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除分类")
    @PreAuthorize("hasAuthority('SCOPE_administrator')")
    public R<Boolean> deleteSort(@PathVariable("id") Long id){
        return R.okForData(sortService.deleteSort(id));
    }
}
