package com.biu.myapi.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatisConfig
 *
 * @author wbbaijq
 */
@Configuration
@MapperScan("com.biu.myapi.mapper")
public class MyBatisConfig {

}
