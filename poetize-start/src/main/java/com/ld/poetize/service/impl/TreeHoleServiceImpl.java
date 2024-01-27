package com.ld.poetize.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ld.poetize.dto.TreeHolePageDTO;
import com.ld.poetize.entity.TreeHole;
import com.ld.poetize.mapper.TreeHoleMapper;
import com.ld.poetize.service.TreeHoleService;
import com.ld.poetize.vo.TreeHoleVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;

/**
 * @Author zuosy
 * @Date 2024/1/27 10:59
 **/
@Service
@RequiredArgsConstructor
public class TreeHoleServiceImpl extends ServiceImpl<TreeHoleMapper, TreeHole> implements TreeHoleService {
    @Override
    public Page<TreeHoleVO> pageList(TreeHolePageDTO treeHolePageDTO) {
        Page<TreeHole> reqPage = Page.of(treeHolePageDTO.getCurrent(), treeHolePageDTO.getSize());
        Page<TreeHole> treeHolePage = baseMapper.selectPage(reqPage, null);
        Page<TreeHoleVO> result = new Page<>();
        if (CollectionUtils.isEmpty(treeHolePage.getRecords())){
            result.setTotal(0L);
            result.setRecords(new ArrayList<>());
            return result;
        }
        result.setTotal(treeHolePage.getTotal());
        result.setRecords(BeanUtil.copyToList(treeHolePage.getRecords(), TreeHoleVO.class));
        return result;
    }

    @Override
    public Boolean deleteTreeHole(Long id) {
        return baseMapper.deleteById(id) > 0;
    }
}
