package com.ld.poetize.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ld.poetize.dto.ArticlePageDTO;
import com.ld.poetize.entity.Article;
import com.ld.poetize.utils.web.BaseMapper;
import com.ld.poetize.vo.ArticleVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author zuosy
 * @Date 2024/1/25 20:26
 **/
@Repository
public interface ArticleMapper extends BaseMapper<Article> {

    Page<ArticleVO> pageList(@Param("page") Page<Article> page, @Param("data") ArticlePageDTO articlePageDTO);


    List<ArticleVO> listSortArticle();

    /**
     * 根据id获取文章详情
     * @param id
     * @return
     */
    ArticleVO getArticleById(Long id);
}
