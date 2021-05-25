package com.lhh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ImgServiceApplication{

    public static void main(String[] args) {
        SpringApplication.run(ImgServiceApplication.class, args);
    }
//    public static void main(String[] args) {
//        new SpringApplicationBuilder(ImgServiceApplication.class).run(args);
//        System.out.println(" ===项目启动成功===");
//    }
//
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        // TODO Auto-generated method stub
//        return builder.sources(ImgServiceApplication.class);
//    }
}
