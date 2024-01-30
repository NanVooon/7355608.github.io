package com.ld.poetize.controller;

import com.ld.poetize.service.HistoryInfoService;
import com.ld.poetize.utils.web.R;
import com.ld.poetize.vo.HistoryInfoVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zuosy
 * @Date 2024/1/27 9:09
 **/
@Tag(name = "历史信息")
@RestController
@RequestMapping("/history_info")
@RequiredArgsConstructor
public class HistoryInfoController {

    private final HistoryInfoService historyInfoService;

    @GetMapping("/getHistoryInfo")
    @Operation(summary = "获取网站统计信息")
    @PreAuthorize("hasAuthority('SCOPE_administrator')")
    public R<HistoryInfoVO> getHistoryInfo(){
        return R.okForData(historyInfoService.getHistoryInfo());
    }


    @GetMapping("/addHistoryInfo")
    @Operation(summary = "添加网站统计信息")
    @PermitAll
    public R<Boolean> addHistoryInfo(HttpServletRequest request){
        return R.okForData(historyInfoService.addHistoryInfo(request));
    }
}
