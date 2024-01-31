package com.ld.poetize.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ld.poetize.dto.ArticleDTO;
import com.ld.poetize.dto.ArticlePageDTO;
import com.ld.poetize.service.ArticleService;
import com.ld.poetize.utils.web.R;
import com.ld.poetize.vo.ArticleVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author zuosy
 * @Date 2024/1/25 20:36
 **/
@Tag(name = "文章信息")
@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/list")
    @Operation(summary = "分页数据列表")
    @PreAuthorize("hasAuthority('SCOPE_administrator')")
    public R<Page<ArticleVO>> pageList(@Validated ArticlePageDTO articlePageDTO){
        return R.okForData(articleService.pageList(articlePageDTO));
    }

    @PostMapping("/saveArticle")
    @Operation(summary = "新增文章")
    @PreAuthorize("hasAuthority('SCOPE_administrator')")
    public R<Boolean> saveArticle(@RequestBody @Validated(Insert.class) ArticleDTO articleDTO){
        return R.okForData(articleService.saveArticle(articleDTO));
    }

    @GetMapping("/{id}")
    @Operation(summary = "文章详情")
    @PreAuthorize("hasAuthority('SCOPE_administrator')")
    public R<ArticleVO> getArticleById(@PathVariable("id") Long id){
        return R.okForData(articleService.getArticleById(id));
    }

    @PostMapping("/updateArticle")
    @Operation(summary = "修改文章")
    @PreAuthorize("hasAuthority('SCOPE_administrator')")
    public R<Boolean> updateArticle(@RequestBody @Validated(Update.class)ArticleDTO articleDTO){
        return R.okForData(articleService.updateArticle(articleDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除文章")
    @PreAuthorize("hasAuthority('SCOPE_administrator')")
    public R<Boolean> deleteArticle(@PathVariable("id") Long id){
        return R.okForData(articleService.deleteArticle(id));
    }



    /*前端页面接口*/
    @GetMapping("/front/listArticle")
    @Operation(summary = "首页文章列表")
    public R<Page<ArticleVO>> listArticle(ArticlePageDTO articlePageDTO){
        return R.okForData(articleService.listArticleFront(articlePageDTO));
    }

    @GetMapping("/front/listSortArticle")
    @Operation(summary = "首页各分类获取六条")
    public R<Map<Long, List<ArticleVO>>> listSortArticle(){
        return R.okForData(articleService.listSortArticle());
    }

    @GetMapping("/front/{id}")
    @Operation(summary = "首页获取文章详情")
    public R<ArticleVO> getArticleByIdFront(@PathVariable("id") Long id){
        return R.okForData(articleService.getArticleById(id));
    }
}
