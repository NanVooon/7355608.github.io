package com.ld.poetize.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ld.poetize.entity.HistoryInfo;
import com.ld.poetize.mapper.HistoryInfoMapper;
import com.ld.poetize.service.HistoryInfoService;
import com.ld.poetize.vo.HistoryInfoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zuosy
 * @Date 2024/1/27 9:08
 **/
@Service
@RequiredArgsConstructor
public class HistoryInfoServiceImpl extends ServiceImpl<HistoryInfoMapper, HistoryInfo> implements HistoryInfoService {
    @Override
    public HistoryInfoVO getHistoryInfo() {
        HistoryInfoVO result = new HistoryInfoVO();
        List<HistoryInfoVO.Child> todayStatistics = baseMapper.todayStatistics();
        List<HistoryInfoVO.Child> yesterdayStatistics = baseMapper.yesterdayStatistics();
        List<HistoryInfoVO.Child> provinceTOP = baseMapper.provinceTOP();
        List<HistoryInfoVO.Child> ipTOP = baseMapper.ipTOP();
        result.setTodayStatistics(todayStatistics);
        result.setYesterdayStatistics(yesterdayStatistics);
        result.setProvinceTOP(provinceTOP);
        result.setIpTOP(ipTOP);
        return result;
    }
}
