package com.ld.poetize.service;

import com.ld.poetize.vo.UploadVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author zuosy
 * @Date 2024/1/27 15:18
 **/
public interface QiniuService {

    UploadVO upload(MultipartFile file);
}
