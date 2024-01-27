package com.ld.poetize.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ld.poetize.dto.CommentPageDTO;
import com.ld.poetize.service.CommentService;
import com.ld.poetize.utils.web.R;
import com.ld.poetize.vo.CommentVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author zuosy
 * @Date 2024/1/25 21:08
 **/
@Tag(name = "评论信息")
@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/pageList")
    @Operation(summary = "分页数据列表")
    @PreAuthorize("hasAuthority('SCOPE_administrator')")
    public R<Page<CommentVO>> pageList(@Validated CommentPageDTO commentPageDTO){
        return R.okForData(commentService.pageList(commentPageDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除评论")
    @PreAuthorize("hasAuthority('SCOPE_administrator')")
    public R<Boolean> deleteComment(@PathVariable("id") Long id){
        return R.okForData(commentService.deleteComment(id));
    }
}
