package com.ld.poetize.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ld.poetize.dto.LabelDTO;
import com.ld.poetize.entity.Label;
import com.ld.poetize.mapper.LabelMapper;
import com.ld.poetize.service.LabelService;
import com.ld.poetize.vo.LabelVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zuosy
 * @Date 2024/1/25 20:59
 **/
@Service
@RequiredArgsConstructor
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements LabelService {

    @Override
    public Boolean saveLabel(LabelDTO labelDTO) {
        Long checkName = baseMapper.selectCount(new LambdaQueryWrapper<Label>()
                .eq(Label::getLabelName, labelDTO.getLabelName()));
        if (checkName > 0L){
            throw new IllegalArgumentException("该标签已存在");
        }
        Label label = BeanUtil.copyProperties(labelDTO, Label.class);
        return baseMapper.insert(label) > 0;
    }

    @Override
    public Boolean updateLabel(LabelDTO labelDTO) {
        Long checkName = baseMapper.selectCount(new LambdaQueryWrapper<Label>()
                .eq(Label::getLabelName, labelDTO.getLabelName())
                .ne(Label::getId, labelDTO.getId()));
        if (checkName > 0L){
            throw new IllegalArgumentException("该标签已存在");
        }
        Label label = BeanUtil.copyProperties(labelDTO, Label.class);
        return baseMapper.updateById(label) > 0;
    }

    @Override
    public Boolean deleteLabel(Long id) {
        return baseMapper.deleteById(id) > 0;
    }

    @Override
    public List<LabelVO> listLabel() {
        return baseMapper.statistics();
    }
}
