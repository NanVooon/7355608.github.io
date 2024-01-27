package com.ld.poetize.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ld.poetize.dto.TreeHolePageDTO;
import com.ld.poetize.entity.TreeHole;
import com.ld.poetize.vo.TreeHoleVO;

/**
 * @Author zuosy
 * @Date 2024/1/27 10:59
 **/
public interface TreeHoleService extends IService<TreeHole> {

    /**
     * 分页数据
     * @param treeHolePageDTO
     * @return
     */
    Page<TreeHoleVO> pageList(TreeHolePageDTO treeHolePageDTO);

    /**
     * 删除信息
     * @param id
     * @return
     */
    Boolean deleteTreeHole(Long id);
}
