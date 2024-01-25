package com.ld.poetize.mapper;

import com.ld.poetize.entity.Sort;
import com.ld.poetize.utils.web.BaseMapper;
import com.ld.poetize.vo.SortVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author zuosy
 * @Date 2024/1/25 20:55
 **/
@Repository
public interface SortMapper extends BaseMapper<Sort> {

    List<SortVO> statistics();
}
