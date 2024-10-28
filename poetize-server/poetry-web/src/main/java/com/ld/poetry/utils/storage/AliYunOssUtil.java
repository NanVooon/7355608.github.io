package com.ld.poetry.utils.storage;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.ld.poetry.entity.Resource;
import com.ld.poetry.service.ResourceService;
import com.ld.poetry.constants.CommonConst;
import com.ld.poetry.vo.FileVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
@ConditionalOnProperty(name = "aliyun.oss.enable", havingValue = "true")
public class AliYunOssUtil implements StoreService {

    @Value("${aliyun.oss.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyun.oss.bucketName}")
    private String bucketName;

    @Value("${aliyun.oss.downloadUrl}")
    private String downloadUrl;

    @Autowired
    private ResourceService resourceService;

    @Override
    public void deleteFile(List<String> files) {
        if (CollectionUtils.isEmpty(files)) {
            return;
        }

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            List<String> keys = files.stream()
                    .map(path -> path.replace(downloadUrl, ""))
                    .collect(Collectors.toList());

            DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest(bucketName)
                    .withKeys(keys);

            ossClient.deleteObjects(deleteObjectsRequest);

            for (String key : keys) {
                log.info("文件删除成功：" + key);
                resourceService.lambdaUpdate().eq(Resource::getPath, downloadUrl + key).remove();
            }
        } catch (Exception ex) {
            log.error("文件删除失败：" + ex.getMessage());
        } finally {
            ossClient.shutdown();
        }
    }

    @Override
    public FileVO saveFile(FileVO fileVO) {
        // 实现文件上传逻辑
        return null;
    }

    @Override
    public String getStoreName() {
        return StoreEnum.ALIYUN.getCode();
    }

    public Map<String, Map<String, String>> getFileInfo(List<String> files) {
        Map<String, Map<String, String>> result = new HashMap<>();

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            for (String file : files) {
                String key = file.replace(downloadUrl, "");
                com.aliyun.oss.model.ObjectMetadata objectMetadata = ossClient.getObjectMetadata(bucketName, key);

                Map<String, String> info = new HashMap<>();
                info.put("size", String.valueOf(objectMetadata.getContentLength()));
                info.put("mimeType", objectMetadata.getContentType());
                result.put(key, info);
            }
        } catch (Exception ex) {
            log.error("获取文件信息失败：" + ex.getMessage());
        } finally {
            ossClient.shutdown();
        }

        return result;
    }

    public void saveFileInfo() {
        List<Resource> resourceList = resourceService.lambdaQuery().select(Resource::getPath).list();
        List<String> paths = resourceList.stream().map(Resource::getPath).collect(Collectors.toList());

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucketName);
            ObjectListing objectListing;

            do {
                objectListing = ossClient.listObjects(listObjectsRequest);

                for (OSSObjectSummary objectSummary : objectListing.getObjectSummaries()) {
                    String key = objectSummary.getKey();
                    String path = downloadUrl + key;

                    if (objectSummary.getSize() != 0L && !paths.contains(path)) {
                        Resource resource = new Resource();
                        resource.setPath(path);
                        resource.setType(CommonConst.PATH_TYPE_ASSETS);
                        resource.setSize(Integer.valueOf(Long.toString(objectSummary.getSize())));
                        resource.setMimeType(objectSummary.getType());
                        resource.setStoreType(getStoreName());
                        resource.setUserId(CommonConst.ADMIN_USER_ID);
                        resourceService.save(resource);
                    }
                }

                listObjectsRequest.setMarker(objectListing.getNextMarker());
            } while (objectListing.isTruncated());
        } catch (Exception ex) {
            log.error("同步文件信息失败：" + ex.getMessage());
        } finally {
            ossClient.shutdown();
        }

        System.out.println("同步完成");
    }
}
