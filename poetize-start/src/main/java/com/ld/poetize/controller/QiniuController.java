package com.ld.poetize.controller;

import com.ld.poetize.service.QiniuService;
import com.ld.poetize.utils.web.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author zuosy
 * @Date 2024/1/27 15:59
 **/
@Tag(name = "七牛云文件服务")
@RestController
@RequestMapping("/qiniu")
@RequiredArgsConstructor
public class QiniuController {

    private final QiniuService qiniuService;

    @PostMapping("/upload")
    @Operation(summary = "上传文件")
    public R<String> upload(MultipartFile file){
        return R.okForData(qiniuService.upload(file));
    }
}
