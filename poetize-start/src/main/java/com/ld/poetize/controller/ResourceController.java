package com.ld.poetize.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ld.poetize.dto.ResourceDTO;
import com.ld.poetize.dto.ResourcePageDTO;
import com.ld.poetize.service.ResourceService;
import com.ld.poetize.utils.web.R;
import com.ld.poetize.vo.ResourceVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author zuosy
 * @Date 2024/1/27 16:32
 **/
@Tag(name = "资源信息")
@RestController
@RequestMapping("/resource")
@RequiredArgsConstructor
public class ResourceController {

    private final ResourceService resourceService;

    @GetMapping("/pageList")
    @Operation(summary = "分页数据")
    @PreAuthorize("hasAuthority('SCOPE_administrator')")
    public R<Page<ResourceVO>> pageList(@Validated ResourcePageDTO resourcePageDTO){
        return R.okForData(resourceService.pageList(resourcePageDTO));
    }

    @PostMapping("/saveResource")
    @Operation(summary = "新增资源")
    @PreAuthorize("hasAuthority('SCOPE_administrator')")
    public R<Boolean> saveMinio(@RequestBody @Validated(Insert.class)ResourceDTO resourceDTO){
        return R.okForData(resourceService.saveResource(resourceDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除资源")
    @PreAuthorize("hasAuthority('SCOPE_administrator')")
    public R<Boolean> deleteResource(@PathVariable("id") Long id){
        return R.okForData(resourceService.deleteResource(id));
    }
}
