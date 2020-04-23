package com.baijia.lhy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.baijia.lhy.mapper") //扫码mapper目录下所有的类为mysql相关类
public class BaijiaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaijiaApplication.class, args);
    }

}
