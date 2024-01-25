package com.ld.poetize.utils.web;

import java.util.Collection;

/**
 * @Author zuosy
 * @Date 2024/1/25 20:32
 **/
public interface BaseMapper<T> extends com.baomidou.mybatisplus.core.mapper.BaseMapper<T> {

    /**
     * 批量插入
     * @param entityList
     * @return
     */
    Integer insertBatchSomeColumn(Collection<T> entityList);
}