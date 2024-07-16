package com.ycr.postdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.ycr.postdemo")
@MapperScan("com.ycr.postdemo.mapper")
public class PostDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostDemoApplication.class, args);
    }

}
