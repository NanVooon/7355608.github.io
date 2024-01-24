package com.ld.poetize.handler;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;

import java.util.List;

/**
 * @Author zuosy
 * @Date 2024/1/24 19:45
 **/
public class InsertBatchInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
        //获取mybatis里所有方法
        List<AbstractMethod> methodList = super.getMethodList(mapperClass, tableInfo);
        //添加批量插入方法
        methodList.add(new InsertBatchSomeColumn(i -> i.getFieldFill() != FieldFill.UPDATE));
        return methodList;
    }
}
