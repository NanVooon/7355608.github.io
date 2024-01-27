package com.ld.poetize.mapper;

import com.ld.poetize.entity.HistoryInfo;
import com.ld.poetize.utils.web.BaseMapper;
import com.ld.poetize.vo.HistoryInfoChildVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author zuosy
 * @Date 2024/1/27 9:07
 **/
@Repository
public interface HistoryInfoMapper extends BaseMapper<HistoryInfo> {

    /**
     * 今日统计
     * @return
     */
    List<HistoryInfoChildVO> todayStatistics();

    /**
     * 昨日统计
     * @return
     */
    List<HistoryInfoChildVO> yesterdayStatistics();

    /**
     * 省访问TOP10
     * @return
     */
    List<HistoryInfoChildVO> provinceTOP();

    /**
     * IP访问TOP10
     * @return
     */
    List<HistoryInfoChildVO> ipTOP();
}
