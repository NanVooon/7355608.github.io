package com.ld.poetize.config;

import io.minio.MinioClient;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author zuosy
 * @Date 2024/1/27 12:31
 **/
@Data
@Configuration
@ConfigurationProperties(prefix="minio")
public class MinIoConfig {

    /**
     * 是否开启
     */
    @Value("${minio.enable}")
    private Boolean enable;

    /**
     * 桶名称
     */
    @Value("${minio.bucket}")
    private String bucket;

    /**
     * 访问密钥
     */
    @Value("${minio.accessKey}")
    private String accessKey;

    /**
     * 密钥
     */
    @Value("${minio.secretKey}")
    private String secretKey;

    /**
     * 访问api Url
     */
    @Value("${minio.endpoint}")
    private String endpoint;

    /**
     * 创建MinIo客户端
     *
     * @return
     */
    @Bean
    public MinioClient minioClient() {
        if (enable){
            return MinioClient.builder()
                    .endpoint(endpoint)
                    .credentials(accessKey, secretKey)
                    .build();
        }
        return null;
    }
}
