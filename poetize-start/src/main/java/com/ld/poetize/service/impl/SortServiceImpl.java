package com.ld.poetize.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ld.poetize.dto.SortDTO;
import com.ld.poetize.entity.Label;
import com.ld.poetize.entity.Sort;
import com.ld.poetize.mapper.LabelMapper;
import com.ld.poetize.mapper.SortMapper;
import com.ld.poetize.service.SortService;
import com.ld.poetize.vo.LabelVO;
import com.ld.poetize.vo.SortVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author zuosy
 * @Date 2024/1/25 20:55
 **/
@Service
@RequiredArgsConstructor
public class SortServiceImpl extends ServiceImpl<SortMapper, Sort> implements SortService {

    private final LabelMapper labelMapper;

    @Override
    public List<SortVO> allSortList() {
        List<SortVO> sortVOList = baseMapper.statistics();
        List<LabelVO> labelVOList = labelMapper.statistics();
        sortVOList.forEach(sort -> {
            List<LabelVO> voList = labelVOList.stream()
                    .filter(f -> Objects.equals(f.getSortId(), sort.getSortId()))
                    .collect(Collectors.toList());
            sort.setLabels(voList);
        });
        return sortVOList;
    }

    @Override
    public Boolean saveSort(SortDTO sortDTO) {
        Long checkName = baseMapper.selectCount(new LambdaQueryWrapper<Sort>()
                .eq(Sort::getSortName, sortDTO.getSortName()));
        if (checkName > 0L){
            throw new IllegalArgumentException("该分类已存在");
        }
        Sort sort = BeanUtil.copyProperties(sortDTO, Sort.class);
        return baseMapper.insert(sort) > 0;
    }

    @Override
    public Boolean updateSort(SortDTO sortDTO) {
        Long checkName = baseMapper.selectCount(new LambdaQueryWrapper<Sort>()
                .eq(Sort::getSortName, sortDTO.getSortName())
                .ne(Sort::getId, sortDTO.getId()));
        if (checkName > 0L){
            throw new IllegalArgumentException("该分类已存在");
        }
        Sort sort = BeanUtil.copyProperties(sortDTO, Sort.class);
        return baseMapper.updateById(sort) > 0;
    }

    @Override
    public Boolean deleteSort(Long id) {
        Long checkCount = labelMapper.selectCount(new LambdaQueryWrapper<Label>()
                .eq(Label::getSortId, id));
        if (checkCount > 0L){
            throw new IllegalArgumentException("存在标签数据，不可删除");
        }
        return baseMapper.deleteById(id) > 0;
    }
}
