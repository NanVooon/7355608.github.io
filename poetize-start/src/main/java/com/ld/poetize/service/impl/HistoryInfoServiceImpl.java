package com.ld.poetize.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ld.poetize.entity.HistoryInfo;
import com.ld.poetize.mapper.HistoryInfoMapper;
import com.ld.poetize.service.HistoryInfoService;
import com.ld.poetize.utils.WebUtils;
import com.ld.poetize.vo.HistoryInfoChildVO;
import com.ld.poetize.vo.HistoryInfoVO;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.lionsoul.ip2region.xdb.Searcher;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zuosy
 * @Date 2024/1/27 9:08
 **/
@Service
@RequiredArgsConstructor
public class HistoryInfoServiceImpl extends ServiceImpl<HistoryInfoMapper, HistoryInfo> implements HistoryInfoService {

    private Searcher searcher;

    @PostConstruct
    public void init() {
        try {
            searcher = Searcher.newWithBuffer(IOUtils.toByteArray(new ClassPathResource("ip2region.xdb").getInputStream()));
        } catch (Exception e) {
            throw new RuntimeException("初始化searcher异常", e);
        }
    }


    @Override
    public HistoryInfoVO getHistoryInfo() {
        HistoryInfoVO result = new HistoryInfoVO();
        List<HistoryInfoChildVO> todayStatistics = baseMapper.todayStatistics();
        List<HistoryInfoChildVO> yesterdayStatistics = baseMapper.yesterdayStatistics();
        List<HistoryInfoChildVO> provinceTOP = baseMapper.provinceTOP();
        List<HistoryInfoChildVO> ipTOP = baseMapper.ipTOP();
        result.setTodayStatistics(todayStatistics);
        result.setYesterdayStatistics(yesterdayStatistics);
        result.setProvinceTOP(provinceTOP);
        result.setIpTOP(ipTOP);
        return result;
    }

    @Override
    public Boolean addHistoryInfo(HttpServletRequest request) {
        String clientRealIp = WebUtils.getClientRealIp(request);
        if (StringUtils.isEmpty(clientRealIp)) {
            return Boolean.TRUE;
        }
        //String username = SecurityUtil.getLoginUser().getUsername();
        String username = null;
        HistoryInfo historyInfo = new HistoryInfo();
        historyInfo.setIp(clientRealIp);
        historyInfo.setUsername(username);
        if (ObjectUtils.isNotEmpty(searcher)){
            try {
                String search = searcher.search(clientRealIp);
                String[] region = search.split("\\|");
                if (!"0".equals(region[0])) {
                    historyInfo.setNation(region[0]);
                }
                if (!"0".equals(region[2])) {
                    historyInfo.setProvince(region[2]);
                }
                if (!"0".equals(region[3])) {
                    historyInfo.setCity(region[3]);
                }
            } catch (Exception e) {
                throw new RuntimeException("根据ip获取用户定位异常", e);
            }
        }
        return save(historyInfo);
    }
}
