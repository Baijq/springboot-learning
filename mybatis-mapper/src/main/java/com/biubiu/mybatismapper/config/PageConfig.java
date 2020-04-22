package com.biubiu.mybatismapper.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * PageConfig
 *
 * @author biubiu
 */
@Configuration
public class PageConfig {
    /**
     * 分页插件 PageHelper
     */
    @Bean
    public PageHelper getPageHelperConfig() {
        PageHelper pageHelper=new PageHelper();
        Properties properties=new Properties();
        properties.setProperty("helperDialect","mysql");
        properties.setProperty("reasonable","true");
        properties.setProperty("supportMethodsArguments","true");
        properties.setProperty("params","count=countSql");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
