package com.ld.poetize.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ld.poetize.dto.ArticleDTO;
import com.ld.poetize.dto.ArticlePageDTO;
import com.ld.poetize.entity.Article;
import com.ld.poetize.vo.ArticleVO;

/**
 * @Author zuosy
 * @Date 2024/1/25 20:33
 **/
public interface ArticleService extends IService<Article> {

    /**
     * 分页数据列表
     * @param articlePageDTO
     * @return
     */
    Page<ArticleVO> pageList(ArticlePageDTO articlePageDTO);

    /**
     * 新增文章
     * @param articleDTO
     * @return
     */
    Boolean saveArticle(ArticleDTO articleDTO);

    /**
     * 文章详情
     * @param id
     * @return
     */
    ArticleVO getArticleById(Long id);

    /**
     * 修改文章
     * @param articleDTO
     * @return
     */
    Boolean updateArticle(ArticleDTO articleDTO);

    /**
     * 删除文章
     * @param id
     * @return
     */
    Boolean deleteArticle(Long id);
}
