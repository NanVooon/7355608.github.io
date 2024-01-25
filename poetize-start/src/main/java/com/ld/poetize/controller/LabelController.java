package com.ld.poetize.controller;

import com.ld.poetize.dto.LabelDTO;
import com.ld.poetize.service.LabelService;
import com.ld.poetize.utils.web.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author zuosy
 * @Date 2024/1/25 21:03
 **/
@Tag(name = "标签信息")
@RestController
@RequestMapping("/label")
@RequiredArgsConstructor
public class LabelController {

    private final LabelService labelService;

    @PostMapping("/saveLabel")
    @Operation(summary = "新增标签")
    @PreAuthorize("hasAuthority('SCOPE_administrator')")
    public R<Boolean> saveLabel(@RequestBody @Validated(Insert.class) LabelDTO labelDTO){
        return R.okForData(labelService.saveLabel(labelDTO));
    }

    @PostMapping("/updateLabel")
    @Operation(summary = "修改标签")
    @PreAuthorize("hasAuthority('SCOPE_administrator')")
    public R<Boolean> updateLabel(@RequestBody @Validated(Update.class)LabelDTO labelDTO){
        return R.okForData(labelService.updateLabel(labelDTO));
    }

    @GetMapping("/deleteLabel")
    @Operation(summary = "删除标签")
    @PreAuthorize("hasAuthority('SCOPE_administrator')")
    public R<Boolean> deleteLabel(@RequestParam("id") Long id){
        return R.okForData(labelService.deleteLabel(id));
    }
}
