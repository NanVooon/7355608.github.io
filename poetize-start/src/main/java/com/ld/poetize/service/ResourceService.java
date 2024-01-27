package com.ld.poetize.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ld.poetize.dto.ResourceDTO;
import com.ld.poetize.dto.ResourcePageDTO;
import com.ld.poetize.entity.Resource;
import com.ld.poetize.vo.ResourceVO;

/**
 * @Author zuosy
 * @Date 2024/1/27 16:31
 **/
public interface ResourceService extends IService<Resource> {

    /**
     * 分页数据
     * @param resourcePageDTO
     * @return
     */
    Page<ResourceVO> pageList(ResourcePageDTO resourcePageDTO);

    /**
     * 新增资源
     * @param resourceDTO
     * @return
     */
    Boolean saveResource(ResourceDTO resourceDTO);

    /**
     * 删除资源
     * @param id
     * @return
     */
    Boolean deleteResource(Long id);
}
