package com.biubiu.mybatismapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.biubiu.mybatismapper.mapper")//注意是tk.mybatis.spring.annotation.MapperScan;
public class MybatisMapperApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisMapperApplication.class, args);
    }

}
