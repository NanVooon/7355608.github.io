package com.ld.poetize.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ld.poetize.dto.CommentPageDTO;
import com.ld.poetize.entity.Comment;
import com.ld.poetize.mapper.CommentMapper;
import com.ld.poetize.service.CommentService;
import com.ld.poetize.vo.CommentVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

/**
 * @Author zuosy
 * @Date 2024/1/25 21:08
 **/
@Service
@RequiredArgsConstructor
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Override
    public Page<CommentVO> pageList(CommentPageDTO commentPageDTO) {
        Page<Comment> reqPage = Page.of(commentPageDTO.getCurrent(), commentPageDTO.getSize());
        Page<Comment> commentPage = baseMapper.selectPage(reqPage, new LambdaQueryWrapper<Comment>()
                .eq(StringUtils.hasText(commentPageDTO.getSource()), Comment::getSource, commentPageDTO.getSource())
                .eq(StringUtils.hasText(commentPageDTO.getCommentType()), Comment::getType, commentPageDTO.getCommentType()));

        Page<CommentVO> result = new Page<>();
        if (CollectionUtils.isEmpty(commentPage.getRecords())){
            result.setTotal(0L);
            result.setRecords(new ArrayList<>());
            return result;
        }
        result.setTotal(commentPage.getTotal());
        result.setRecords(BeanUtil.copyToList(commentPage.getRecords(), CommentVO.class));
        return result;
    }

    @Override
    public Boolean deleteComment(Long id) {
        return baseMapper.deleteById(id) > 0;
    }
}
