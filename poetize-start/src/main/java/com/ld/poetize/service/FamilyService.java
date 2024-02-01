package com.ld.poetize.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ld.poetize.dto.FamilyDTO;
import com.ld.poetize.dto.FamilyPageDTO;
import com.ld.poetize.entity.Family;
import com.ld.poetize.vo.FamilyVO;

import java.util.List;

/**
 * @Author zuosy
 * @Date 2024/1/27 17:14
 **/
public interface FamilyService extends IService<Family> {
    /**
     * 分页数据
     * @param familyPageDTO
     * @return
     */
    Page<FamilyVO> pageList(FamilyPageDTO familyPageDTO);

    /**
     * 保存数据
     * @param familyDTO
     * @return
     */
    Boolean saveFamily(FamilyDTO familyDTO);

    /**
     * 详情
     * @param id
     * @return
     */
    FamilyVO getFamilyById(Long id);

    /**
     * 修改数据
     * @param familyDTO
     * @return
     */
    Boolean updateFamily(FamilyDTO familyDTO);

    /**
     * 删除数据
     * @param id
     * @return
     */
    Boolean deleteFamily(Long id);

    /**
     * 获取列表
     * @return
     */
    List<FamilyVO> getFamilyList();

}
