package com.lhh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan(value = "com.example.shirojwt.mapper")
@EnableSwagger2
public class ShiroJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiroJwtApplication.class, args);
    }

}
