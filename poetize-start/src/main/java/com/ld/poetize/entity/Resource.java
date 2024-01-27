package com.ld.poetize.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author zuosy
 * @Date 2024/1/27 16:27
 **/
@Data
public class Resource {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 资源类型[0:背景图,1:表情包]
     */
    private String type;

    /**
     * 资源路径
     */
    private String path;

    /**
     * 资源内容的大小，单位：字节
     */
    private Integer size;

    /**
     * 文件名称
     */
    private String originalName;

    /**
     * 资源的 MIME 类型
     */
    private String mimeType;

    /**
     * 存储平台
     */
    private String storeType;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
