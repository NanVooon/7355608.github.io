package com.ld.poetize.config;

import com.ld.poetize.enums.QiniuAddress;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import io.minio.MinioClient;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.*;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Objects;

/**
 * @Author zuosy
 * @Date 2024/1/27 12:31
 **/
@Slf4j
@Data
@Configuration
@ConfigurationProperties(prefix = "oss")
public class OssConfig {

    private Minio minio;

    private QiniuClient qiniu;


    @Getter
    @Setter
    public static class Minio {

        /**
         * 是否开启
         */
        @Value("${oss.minio.enable}")
        private Boolean enable = false;
        /**
         * 桶名称
         */
        @Value("${oss.minio.bucket}")
        private String bucket;

        /**
         * 访问密钥
         */
        @Value("${oss.minio.access-key}")
        private String accessKey;

        /**
         * 密钥
         */
        @Value("${oss.minio.secret-key}")
        private String secretKey;

        /**
         * 访问api Url
         */
        @Value("${oss.minio.endpoint}")
        private String endpoint;

        /**
         * 是否开启https
         */
        @Value("${oss.minio.https}")
        private Boolean https = false;

        /**
         * 开启minio服务器https模式
         */
        @ConditionalOnExpression(value = "${oss.minio.enable:true}")
        @Bean
        public MinioClient minioClient() {
            log.info("========初始化Minio客户端========");
            if (https) {
                return MinioClient.builder()
                        .endpoint(endpoint)
                        .credentials(accessKey, secretKey)
                        .httpClient(Objects.requireNonNull(getUnsafeOkHttpClient()))
                        .build();
            } else {
                return MinioClient.builder()
                        .endpoint(endpoint)
                        .credentials(accessKey, secretKey)
                        .build();
            }
        }

        public static OkHttpClient getUnsafeOkHttpClient() {
            try {
                return new OkHttpClient.Builder()
                        .sslSocketFactory(getSSLSocketFactory(), getX509TrustManager())
                        .hostnameVerifier((s, sslSession) -> true)
                        .build();
            } catch (Exception e) {
                log.error("初始化minio异常");
            }
            return null;
        }

        /**
         * description 忽略https证书验证
         */
        private static SSLSocketFactory getSSLSocketFactory() {
            try {
                SSLContext sslContext = SSLContext.getInstance("SSL");
                sslContext.init(null, getTrustManager(), new SecureRandom());
                return sslContext.getSocketFactory();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        private static X509TrustManager getX509TrustManager() {
            X509TrustManager trustManager = null;
            try {
                TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                trustManagerFactory.init((KeyStore) null);
                TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
                if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
                    throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
                }
                trustManager = (X509TrustManager) trustManagers[0];
            } catch (Exception e) {
                log.error("初始化minio异常");
            }

            return trustManager;
        }

        private static TrustManager[] getTrustManager() {
            return new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[]{};
                        }
                    }
            };
        }
    }

    @Getter
    @Setter
    public static class QiniuClient {

        @Value("${oss.qiniu.enable}")
        private Boolean enable = false;

        @Value("${oss.qiniu.access-key}")
        private String accessKey;

        @Value("${oss.qiniu.secret-key}")
        private String secretKey;

        @Value("${oss.qiniu.bucket}")
        private String bucket;

        @Value("${oss.qiniu.download-url}")
        private String downloadUrl;

        @Value("${oss.qiniu.https}")
        private Boolean https = false;

        @Value("${oss.qiniu.address}")
        private QiniuAddress address;

        /**
         * 华东机房,配置自己空间所在的区域
         */
        @ConditionalOnExpression(value = "${oss.qiniu.enable:true}")
        @Bean
        public com.qiniu.storage.Configuration qiniuConfig() {
            if (address.equals(QiniuAddress.HUA_DONG_ZJ)){
                return new com.qiniu.storage.Configuration(Region.huadong());
            }
            if (address.equals(QiniuAddress.HUA_BEI)){
                return new com.qiniu.storage.Configuration(Region.huabei());
            }
            if (address.equals(QiniuAddress.HUA_NAN)){
                return new com.qiniu.storage.Configuration(Region.huanan());
            }
            if (address.equals(QiniuAddress.BEI_MEI)){
                return new com.qiniu.storage.Configuration(Region.beimei());
            }
            if (address.equals(QiniuAddress.XIN_JIA_PO)){
                return new com.qiniu.storage.Configuration(Region.xinjiapo());
            }
            if (address.equals(QiniuAddress.HUA_DONG_ZJ2)){
                return new com.qiniu.storage.Configuration(Region.huadongZheJiang2());
            }
            throw new RuntimeException("七牛云初始化指定区域异常");
        }

        /**
         * 构建一个七牛上传工具实例
         */
        @ConditionalOnExpression(value = "${oss.qiniu.enable:true}")
        @Bean
        public UploadManager uploadManager() {
            return new UploadManager(qiniuConfig());
        }

        /**
         * 认证信息实例 * @return
         */
        @ConditionalOnExpression(value = "${oss.qiniu.enable:true}")
        @Bean
        public Auth auth() {
            return Auth.create(accessKey, secretKey);
        }

        /**
         * 构建七牛空间管理实例
         */
        @ConditionalOnExpression(value = "${oss.qiniu.enable:true}")
        @Bean
        public BucketManager bucketManager() {
            return new BucketManager(auth(), qiniuConfig());
        }
    }
}
