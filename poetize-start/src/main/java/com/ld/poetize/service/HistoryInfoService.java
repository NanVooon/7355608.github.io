package com.ld.poetize.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ld.poetize.entity.HistoryInfo;
import com.ld.poetize.vo.HistoryInfoVO;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @Author zuosy
 * @Date 2024/1/27 9:08
 **/
public interface HistoryInfoService extends IService<HistoryInfo> {

    /**
     * 获取网站统计信息
     * @return
     */
    HistoryInfoVO getHistoryInfo();


    /**
     * 添加网站统计信息
     * @return
     */
    Boolean addHistoryInfo(HttpServletRequest request);
}
