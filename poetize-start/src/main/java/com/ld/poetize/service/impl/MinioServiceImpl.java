package com.ld.poetize.service.impl;

import com.ld.poetize.config.MinIoConfig;
import com.ld.poetize.service.MinioService;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author zuosy
 * @Date 2024/1/27 12:35
 **/
@Service
@RequiredArgsConstructor
public class MinioServiceImpl implements MinioService {

    private final MinioClient minioClient;

    private final MinIoConfig minIoConfig;

    @Value("${minio.bucket}")
    private String bucket;

    @Override
    public String upload(MultipartFile file) {
        //文件名
        String filename = file.getOriginalFilename();
        //文件流
        InputStream inputStream;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //文件大小
        long size = file.getSize();
        //存储方法 putObject
        try {
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucket)
                    .object(filename)
                    .stream(inputStream, size, -1)
                    .contentType(file.getContentType())
                    .build());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return minIoConfig.getEndpoint() + "/" + bucket + "/" + filename;
    }
}
