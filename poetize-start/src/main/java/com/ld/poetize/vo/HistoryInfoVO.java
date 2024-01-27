package com.ld.poetize.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @Author zuosy
 * @Date 2024/1/27 9:11
 **/
@Data
public class HistoryInfoVO {

    @Schema(description = "今日统计")
    private List<Child> todayStatistics;

    @Schema(description = "昨日统计")
    private List<Child> yesterdayStatistics;

    @Schema(description = "省访问TOP10")
    private List<Child> provinceTOP;

    @Schema(description = "IP访问TOP10")
    private List<Child> ipTOP;


    @Data
    public static class Child{

        @Schema(description = "key")
        private String key;

        @Schema(description = "value")
        private String value;
    }
}
