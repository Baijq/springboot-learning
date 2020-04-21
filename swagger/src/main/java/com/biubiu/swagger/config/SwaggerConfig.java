package com.biubiu.swagger.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SwaggerConfig
 *
 * @author baijq
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Value("${swagger.show}")
    private boolean swaggerShow;

    /**
     * swaggerRestFulApi
     */
    @Bean
    public Docket swaggerRestFulApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                //可以配置开关来使用
                .enable(swaggerShow)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .build();
    }

    /**
     * 构建 api文档的详细信息函数
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SpringBoot 测试使用 Swagger2 构建 RestFul APIs")
//                .contact(new Contact("一蓑烟雨任平生", "https://www.baidu.com", "mail@163.com"))
                .description("API 描述")
                .version("1.0")
                .build();
    }

}
