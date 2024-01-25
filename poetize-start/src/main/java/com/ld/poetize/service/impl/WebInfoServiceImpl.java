package com.ld.poetize.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ld.poetize.entity.Label;
import com.ld.poetize.entity.Sort;
import com.ld.poetize.entity.WebInfo;
import com.ld.poetize.mapper.LabelMapper;
import com.ld.poetize.mapper.SortMapper;
import com.ld.poetize.mapper.WebInfoMapper;
import com.ld.poetize.service.WebInfoService;
import com.ld.poetize.vo.LabelAndSortVO;
import com.ld.poetize.vo.WebInfoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zuosy
 * @Date 2024/1/25 21:21
 **/
@Service
@RequiredArgsConstructor
public class WebInfoServiceImpl extends ServiceImpl<WebInfoMapper, WebInfo> implements WebInfoService {

    private final SortMapper sortMapper;

    private final LabelMapper labelMapper;

    @Override
    public WebInfoVO getWebInfo() {
        WebInfo webInfo = baseMapper.selectOne(null);
        return BeanUtil.copyProperties(webInfo, WebInfoVO.class);
    }

    @Override
    public LabelAndSortVO listSortAndLabel() {
        List<Sort> sortList = sortMapper.selectList(null);
        List<Label> labelList = labelMapper.selectList(null);
        List<LabelAndSortVO.Sort> sorts = BeanUtil.copyToList(sortList, LabelAndSortVO.Sort.class);
        List<LabelAndSortVO.Label> labels = BeanUtil.copyToList(labelList, LabelAndSortVO.Label.class);
        LabelAndSortVO result = new LabelAndSortVO();
        result.setSorts(sorts);
        result.setLabels(labels);
        return result;
    }
}
