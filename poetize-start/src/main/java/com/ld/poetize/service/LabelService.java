package com.ld.poetize.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ld.poetize.dto.LabelDTO;
import com.ld.poetize.entity.Label;

/**
 * @Author zuosy
 * @Date 2024/1/25 20:59
 **/
public interface LabelService extends IService<Label> {

    /**
     * 新增标签
     * @param labelDTO
     * @return
     */
    Boolean saveLabel(LabelDTO labelDTO);

    /**
     * 修改标签
     * @param labelDTO
     * @return
     */
    Boolean updateLabel(LabelDTO labelDTO);

    /**
     * 删除标签
     * @param id
     * @return
     */
    Boolean deleteLabel(Long id);
}
