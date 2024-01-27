package com.ld.poetize.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ld.poetize.dto.ResourceDTO;
import com.ld.poetize.dto.ResourcePageDTO;
import com.ld.poetize.entity.Resource;
import com.ld.poetize.mapper.ResourceMapper;
import com.ld.poetize.service.ResourceService;
import com.ld.poetize.vo.ResourceVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

/**
 * @Author zuosy
 * @Date 2024/1/27 16:31
 **/
@Service
@RequiredArgsConstructor
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {


    @Override
    public Page<ResourceVO> pageList(ResourcePageDTO resourcePageDTO) {
        Page<Resource> reqPage = Page.of(resourcePageDTO.getCurrent(), resourcePageDTO.getSize());
        Page<Resource> resourcePage = baseMapper.selectPage(reqPage, new LambdaQueryWrapper<Resource>()
                .eq(StringUtils.hasText(resourcePageDTO.getResourceType()), Resource::getType, resourcePageDTO.getResourceType()));
        Page<ResourceVO> result = new Page<>();
        if (CollectionUtils.isEmpty(resourcePage.getRecords())){
            result.setTotal(0L);
            result.setRecords(new ArrayList<>());
            return result;
        }
        result.setTotal(resourcePage.getTotal());
        result.setRecords(BeanUtil.copyToList(resourcePage.getRecords(), ResourceVO.class));
        return result;
    }

    @Override
    public Boolean saveResource(ResourceDTO resourceDTO) {
        Resource resource = BeanUtil.copyProperties(resourceDTO, Resource.class);
        return baseMapper.insert(resource) > 0;
    }

    @Override
    public Boolean deleteResource(Long id) {
        return baseMapper.deleteById(id) > 0;
    }
}
