package com.ld.poetize.service.impl;

import com.ld.poetize.service.QiniuService;
import com.ld.poetize.vo.UploadVO;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author zuosy
 * @Date 2024/1/27 15:18
 **/
@Service
@RequiredArgsConstructor
public class QiniuServiceImpl implements QiniuService {

    private final UploadManager uploadManager;

    private final Auth auth;

    @Value("${oss.qiniu.download-url}")
    private String downloadUrl;

    @Value("${oss.qiniu.bucket}")
    private String bucket;

    @Override
    public UploadVO upload(MultipartFile file) {
        UploadVO uploadVO = new UploadVO();
        try {
            //文件字节
            byte[] fileBytes = file.getBytes();
            //文件名
            String filename = file.getOriginalFilename();
            //文件类型
            String mime = file.getContentType();
            //七牛云上传token
            String uploadToken = auth.uploadToken(bucket);

            Response response = this.uploadManager.put(fileBytes, filename, uploadToken, getPutPolicy(),
                    mime, false);
            if (response.statusCode == 200) {
                uploadVO.setPath(downloadUrl+ filename);
                uploadVO.setSize(Integer.valueOf(String.valueOf(file.getSize())));
                uploadVO.setMimeType(file.getContentType());
                uploadVO.setOriginalName(filename);
                return uploadVO;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return uploadVO;
    }

    /**
     * 定义七牛云上传的相关策略
     */
    private StringMap getPutPolicy() {
        StringMap stringMap = new StringMap();
        stringMap.put("returnBody",
                "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"width\":$(imageInfo.width), \"height\":${imageInfo.height}}");
        return stringMap;
    }
}
