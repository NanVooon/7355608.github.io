package com.ld.poetry.controller;

import com.ld.poetry.utils.storage.AliYunOssUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/aliyun")
@ConditionalOnBean(AliYunOssUtil.class)
public class AliYunOssController {
    @Resource
    private AliYunOssUtil aliYunOssUtil;
}
