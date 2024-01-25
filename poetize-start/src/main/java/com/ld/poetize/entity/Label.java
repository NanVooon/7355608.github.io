package com.ld.poetize.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @Author zuosy
 * @Date 2024/1/25 20:58
 **/
@Data
public class Label {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 分类ID
     */
    private Long sortId;

    /**
     * 标签名称
     */
    private String labelName;

    /**
     * 标签描述
     */
    private String labelDescription;
}
