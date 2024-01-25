package com.ld.poetize.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ld.poetize.entity.WebInfo;
import com.ld.poetize.vo.LabelAndSortVO;
import com.ld.poetize.vo.WebInfoVO;

/**
 * @Author zuosy
 * @Date 2024/1/25 21:21
 **/
public interface WebInfoService extends IService<WebInfo> {

    /**
     * 获取网站信息
     * @return
     */
    WebInfoVO getWebInfo();

    /**
     * 获取分类和标签下拉框
     * @return
     */
    LabelAndSortVO listSortAndLabel();
}
