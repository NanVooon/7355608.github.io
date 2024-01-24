package com.ld.poetize;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author zuosy
 * @Date 2024/1/24 19:27
 **/
@SpringBootApplication
@MapperScan(basePackages = {"com.ld.poetize.mapper"})
public class PoetryApplication {

    public static void main(String[] args) {
        SpringApplication.run(PoetryApplication.class, args);
    }
}
