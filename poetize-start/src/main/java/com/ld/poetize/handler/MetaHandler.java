package com.ld.poetize.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Author zuosy
 * @Date 2024/1/24 19:46
 **/
@Component
public class MetaHandler implements MetaObjectHandler {

    //todo 认证中心写完解开注释

    @Override
    public void insertFill(MetaObject metaObject) {
        //this.setFieldValByName("createBy", SecurityUtil.getLoginUser().getUsername(), metaObject);
        this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        //this.setFieldValByName("updateBy", SecurityUtil.getLoginUser().getUsername(), metaObject);
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //this.setFieldValByName("updateBy", SecurityUtil.getLoginUser().getUsername(), metaObject);
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }
}
