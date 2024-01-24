package com.ld.poetize.utils.mapper;

import java.util.Collection;

/**
 * @Author zuosy
 * @Date 2024/1/24 19:46
 **/
public interface BaseMapper<T> extends com.baomidou.mybatisplus.core.mapper.BaseMapper<T> {

    /**
     * 批量插入
     * @param entityList
     * @return
     */
    Integer insertBatchSomeColumn(Collection<T> entityList);
}
