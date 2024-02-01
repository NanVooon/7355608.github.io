package com.ld.poetize.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ld.poetize.dto.FamilyDTO;
import com.ld.poetize.dto.FamilyPageDTO;
import com.ld.poetize.entity.Family;
import com.ld.poetize.mapper.FamilyMapper;
import com.ld.poetize.service.FamilyService;
import com.ld.poetize.vo.FamilyVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author zuosy
 * @Date 2024/1/27 17:14
 **/
@Service
@RequiredArgsConstructor
public class FamilyServiceImpl extends ServiceImpl<FamilyMapper, Family> implements FamilyService {

    @Override
    public Page<FamilyVO> pageList(FamilyPageDTO familyPageDTO) {
        Page<Family> reqPage = Page.of(familyPageDTO.getCurrent(), familyPageDTO.getSize());
        Page<Family> familyPage = baseMapper.selectPage(reqPage, new LambdaQueryWrapper<Family>()
                .eq(Objects.nonNull(familyPageDTO.getStatus()), Family::getStatus, familyPageDTO.getStatus()));
        Page<FamilyVO> result = new Page<>();
        if (CollectionUtils.isEmpty(familyPage.getRecords())){
            result.setTotal(0L);
            result.setRecords(new ArrayList<>());
            return result;
        }
        result.setTotal(familyPage.getTotal());
        result.setRecords(BeanUtil.copyToList(familyPage.getRecords(), FamilyVO.class));
        return result;
    }

    @Override
    public Boolean saveFamily(FamilyDTO familyDTO) {
        Family family = BeanUtil.copyProperties(familyDTO, Family.class);
        return baseMapper.insert(family) > 0;
    }

    @Override
    public FamilyVO getFamilyById(Long id) {
        Family family = baseMapper.selectById(id);
        return BeanUtil.copyProperties(family, FamilyVO.class);
    }

    @Override
    public Boolean updateFamily(FamilyDTO familyDTO) {
        Family family = BeanUtil.copyProperties(familyDTO, Family.class);
        return baseMapper.updateById(family) > 0;
    }

    @Override
    public Boolean deleteFamily(Long id) {
        return baseMapper.deleteById(id) > 0;
    }

    @Override
    public List<FamilyVO> getFamilyList() {
        return lambdaQuery()
                .eq(Family::getStatus, Boolean.TRUE)
                .orderByDesc(Family::getCreateTime)
                .list().stream().map(family -> BeanUtil.copyProperties(family, FamilyVO.class)).collect(Collectors.toList());
    }
}
