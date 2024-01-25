package com.ld.poetize.mapper;

import com.ld.poetize.entity.Label;
import com.ld.poetize.utils.web.BaseMapper;
import com.ld.poetize.vo.LabelVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author zuosy
 * @Date 2024/1/25 20:58
 **/
@Repository
public interface LabelMapper extends BaseMapper<Label> {

    List<LabelVO> statistics();
}
