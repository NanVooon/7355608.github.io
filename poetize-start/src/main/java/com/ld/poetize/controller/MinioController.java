package com.ld.poetize.controller;

import com.ld.poetize.service.MinioService;
import com.ld.poetize.utils.web.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author zuosy
 * @Date 2024/1/27 12:35
 **/
@Tag(name = "minio文件服务")
@RestController
@RequestMapping("/minio")
@RequiredArgsConstructor
@ConditionalOnExpression(value = "${oss.minio.enable:true}")
public class MinioController {

    private final MinioService minioService;

    @PostMapping("/upload")
    @Operation(summary = "上传文件")
    public R<String> upload(MultipartFile file){
        return R.okForData(minioService.upload(file).getPath());
    }
}
