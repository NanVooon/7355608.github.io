package com.ld.poetize.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @Author zuosy
 * @Date 2024/1/25 20:54
 **/
@Data
public class Sort {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 分类名称
     */
    private String sortName;

    /**
     * 分类描述
     */
    private String sortDescription;

    /**
     * 分类类型[0:导航栏分类，1:普通分类]
     */
    private String sortType;

    /**
     * 排序
     */
    private Integer priority;
}
