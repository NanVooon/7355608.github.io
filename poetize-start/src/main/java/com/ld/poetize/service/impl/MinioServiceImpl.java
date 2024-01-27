package com.ld.poetize.service.impl;

import com.ld.poetize.service.MinioService;
import com.ld.poetize.vo.UploadVO;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
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
@ConditionalOnExpression(value = "${oss.minio.enable:true}")
public class MinioServiceImpl implements MinioService {

    private final MinioClient minioClient;

    @Value("${oss.minio.bucket}")
    private String bucket;

    @Value("${oss.minio.endpoint}")
    private String endpoint;

    @Override
    public UploadVO upload(MultipartFile file) {
        UploadVO uploadVO = new UploadVO();
        try {
            boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
            if (!bucketExists){
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
        uploadVO.setPath(endpoint + "/" + bucket + "/" + filename);
        uploadVO.setSize(Integer.valueOf(String.valueOf(size)));
        uploadVO.setMimeType(file.getContentType());
        uploadVO.setOriginalName(filename);
        return uploadVO;
    }
}
