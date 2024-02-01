package com.ld.poetize.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ld.poetize.dto.FamilyDTO;
import com.ld.poetize.dto.FamilyPageDTO;
import com.ld.poetize.service.FamilyService;
import com.ld.poetize.utils.web.R;
import com.ld.poetize.vo.FamilyVO;
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
 * @Date 2024/1/27 17:15
 **/
@Tag(name = "表白墙信息")
@RestController
@RequestMapping("/family")
@RequiredArgsConstructor
public class FamilyController {

    private final FamilyService familyService;

    @GetMapping("/pageList")
    @Operation(summary = "分页数据")
    @PreAuthorize("hasAuthority('SCOPE_administrator')")
    public R<Page<FamilyVO>> pageList(@Validated FamilyPageDTO familyPageDTO){
        return R.okForData(familyService.pageList(familyPageDTO));
    }

    @PostMapping("/saveFamily")
    @Operation(summary = "保存数据")
    @PreAuthorize("hasAuthority('SCOPE_administrator')")
    public R<Boolean> saveFamily(@RequestBody @Validated(Insert.class) FamilyDTO familyDTO){
        return R.okForData(familyService.saveFamily(familyDTO));
    }

    @GetMapping("/{id}")
    @Operation(summary = "详情")
    @PreAuthorize("hasAuthority('SCOPE_administrator')")
    public R<FamilyVO> getFamilyById(@PathVariable("id") Long id){
        return R.okForData(familyService.getFamilyById(id));
    }

    @PostMapping("/updateFamily")
    @Operation(summary = "修改数据")
    @PreAuthorize("hasAuthority('SCOPE_administrator')")
    public R<Boolean> updateFamily(@RequestBody @Validated(Update.class) FamilyDTO familyDTO){
        return R.okForData(familyService.updateFamily(familyDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除数据")
    @PreAuthorize("hasAuthority('SCOPE_administrator')")
    public R<Boolean> deleteFamily(@PathVariable("id") Long id){
        return R.okForData(familyService.deleteFamily(id));
    }


    /*前端页面接口*/
    @GetMapping("/getFamilyList")
    @Operation(summary = "获取family列表")
    public R<List<FamilyVO>> getFamilyList(){
        return R.okForData(familyService.getFamilyList());
    }
}
