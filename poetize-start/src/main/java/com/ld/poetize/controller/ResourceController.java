package com.ld.poetize.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ld.poetize.dto.ResourcePageDTO;
import com.ld.poetize.entity.Resource;
import com.ld.poetize.enums.OssType;
import com.ld.poetize.service.MinioService;
import com.ld.poetize.service.QiniuService;
import com.ld.poetize.service.ResourceService;
import com.ld.poetize.utils.web.R;
import com.ld.poetize.vo.ResourceVO;
import com.ld.poetize.vo.UploadVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author zuosy
 * @Date 2024/1/27 16:32
 **/
@Tag(name = "资源信息")
@RestController
@RequestMapping("/resource")
@RequiredArgsConstructor
public class ResourceController {

    private final ResourceService resourceService;

    private final MinioService minioService;

    private final QiniuService qiniuService;

    @GetMapping("/pageList")
    @Operation(summary = "分页数据")
    @PreAuthorize("hasAuthority('SCOPE_administrator')")
    public R<Page<ResourceVO>> pageList(@Validated ResourcePageDTO resourcePageDTO){
        return R.okForData(resourceService.pageList(resourcePageDTO));
    }

    @PostMapping("/saveMinio/{type}")
    @Operation(summary = "新增资源")
    @PreAuthorize("hasAuthority('SCOPE_administrator')")
    public R<Boolean> saveMinio(MultipartFile file, @PathVariable("type")String type){
        UploadVO upload = minioService.upload(file);
        upload.setType(type);
        upload.setStoreType(OssType.MINIO.name());
        Resource resource = BeanUtil.copyProperties(upload, Resource.class);
        return R.okForData(resourceService.saveResource(resource));
    }

    @PostMapping("/saveQiniu/{type}")
    @Operation(summary = "新增资源")
    @PreAuthorize("hasAuthority('SCOPE_administrator')")
    public R<Boolean> saveQiniu(MultipartFile file, @PathVariable("type")String type){
        UploadVO upload = qiniuService.upload(file);
        upload.setType(type);
        upload.setStoreType(OssType.QI_NIU.name());
        Resource resource = BeanUtil.copyProperties(upload, Resource.class);
        return R.okForData(resourceService.saveResource(resource));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除资源")
    @PreAuthorize("hasAuthority('SCOPE_administrator')")
    public R<Boolean> deleteResource(@PathVariable("id") Long id){
        return R.okForData(resourceService.deleteResource(id));
    }
}
