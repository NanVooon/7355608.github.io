package com.ld.poetize.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ld.poetize.dto.SortDTO;
import com.ld.poetize.entity.Sort;
import com.ld.poetize.vo.SortVO;

import java.util.List;

/**
 * @Author zuosy
 * @Date 2024/1/25 20:55
 **/
public interface SortService extends IService<Sort> {

    /**
     * 获取所有分类信息
     * @return
     */
    List<SortVO> allSortList();

    /**
     * 保存分类信息
     * @param sortDTO
     * @return
     */
    Boolean saveSort(SortDTO sortDTO);

    /**
     * 修改分类信息
     * @param sortDTO
     * @return
     */
    Boolean updateSort(SortDTO sortDTO);

    /**
     * 删除分类
     * @param id
     * @return
     */
    Boolean deleteSort(Long id);
}
