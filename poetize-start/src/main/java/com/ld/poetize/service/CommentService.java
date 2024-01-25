package com.ld.poetize.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ld.poetize.dto.CommentPageDTO;
import com.ld.poetize.entity.Comment;
import com.ld.poetize.vo.CommentVO;

/**
 * @Author zuosy
 * @Date 2024/1/25 21:08
 **/
public interface CommentService extends IService<Comment> {

    /**
     * 分页数据列表
     * @param commentPageDTO
     * @return
     */
    Page<CommentVO> pageList(CommentPageDTO commentPageDTO);

    /**
     * 删除评论
     * @param id
     * @return
     */
    Boolean deleteComment(Long id);
}
