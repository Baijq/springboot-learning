## 六、SpringBoot集成Swagger

### 1. 添加依赖

```xml
<!--swagger-->
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.9.2</version>
</dependency>
<!--swagger-ui-->
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>2.9.2</version>
</dependency>

```

可以提取到properties里，如下：

```xml
<version>2.9.2</version>修改如下：
<version>${swagger.version}</version> 

<proerties>
    <swagger-version>2.9.2</swagger-version>
</proerties>

```

### 2. Swagger配置类

- 配置类如下，加了个开关

  ```java
  package com.bjq.demo.config;
  
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
   * Swagger配置
   *
   * @author baijq
   */
  @Configuration
  @EnableSwagger2
  public class SwaggerConfig {
  
      @Value("${swagger.show}")
      private boolean swaggerShow;
  
      @Bean
      public Docket swaggerRestFulApi() {
          return new Docket(DocumentationType.SWAGGER_2)
                  .enable(swaggerShow)
                  .apiInfo(apiInfo())
                  .select()
                  .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                  .build();
      }
      /**
       * 构建 api文档的详细信息函数
       **/
      private ApiInfo apiInfo() {
          return new ApiInfoBuilder()
                  .title("SpringBoot 测试使用 Swagger2 构建 RestFul APIs")
                  //.contact(new Contact("一蓑烟雨任平生", "https://www.baidu.com", "mail@163.com"))
                  .description("API 描述")
                  .version("1.0")
                  .build();
      }
  }
  
  ```

- application.yml配置开关

  ```yaml
  #swagger 开关
  swagger:
    show: true
  
  ```

### 3. 注解使用

- 注解说明

  - @Api：用在类上，说明该类的作用。可以标记一个Controller类做为swagger文档资源。

    ```java
    @Api(value = "页面不显示", tags = {"接口介绍，在页面中显示","可配置多个，页面显示多个"})
    @RestController
    public class HelloController {
        //TODO
    }
    
    ```

  - ApiOperation：用在Controller里的方法上，说明方法的作用

    - value是该类的简短的叙述
    - nates是该方法的详细描述。

    ```java
    @PostMapping("/user")
    @ApiOperation(value = "添加用户接口", notes = "添加用户接口，参数user")
    public String addUser(@RequestBody UserRequest user) {
        return null;
    }
    
    ```

  - @ApiImplicitParam 与 @ApiImplicitParams

    ```txt
    @ApiImplicitParam注解用于表明前端传入的name参数的名字，required是否为必需项，以及dataType参数类型，以及paramType传递方式（query表示使用url问号的方式传参，这种比较常用，如果使用formData的方式进行传参，那么paramType的值为 form）
    当有多个参数时，需要用@ApiImplicitParams将@ApiImplicitParam包起来
    
    ```

  - @ApiModel：用在类上，表示对类进行说明，用于实体类中的参数接收说明

  - @ApiModelProperty：用于字段，表示对model属性的说明

    ```java
    @Data
    @ApiModel(value = "UserRequest", description = "用户请求参数")
    public class UserRequest {
    
        @ApiModelProperty(value = "用户ID", example = "2", dataType = "Integer", required = true)
        private Integer id;
    
        @ApiModelProperty(value = "用户姓名", example = "张三")
        private String name;
    
    }
    
    ```

  - ApiParam： 用于Controller中方法的参数说明

    ```java
    public String getUserName(@ApiParam(value = "用户编号", required = true) @RequestParam Integer userNumber) {//TODO}
    
    ```

    

- 示例代码

  ```java
  package com.bjq.demo.controller;
  
  import com.bjq.demo.controller.request.UserRequest;
  import io.swagger.annotations.Api;
  import io.swagger.annotations.ApiOperation;
  import io.swagger.annotations.ApiParam;
  import org.springframework.web.bind.annotation.GetMapping;
  import org.springframework.web.bind.annotation.PostMapping;
  import org.springframework.web.bind.annotation.RequestBody;
  import org.springframework.web.bind.annotation.RequestParam;
  import org.springframework.web.bind.annotation.RestController;
  
  @Api(value = "接口说明", tags = {"接口说明"})
  @RestController
  public class HelloController {
  
      @GetMapping("/name")
      @ApiOperation(value = "根据用户编号获取用户姓名", notes = "获取用户名接口: 仅1和2有正确返回")
      public String getUserName(@ApiParam(value = "用户编号", required = true) @RequestParam Integer userNumber) {
          if (userNumber == 1) {
              return "张三丰";
          } else if (userNumber == 2) {
              return "慕容复";
          } else {
              return "未知";
          }
      }
  
      @PostMapping("/user")
      @ApiOperation(value = "添加用户接口", notes = "添加用户接口，参数user")
      public String addUser(@RequestBody UserRequest user) {
          return null;
      }
  
  }
  
  ```

### 4. 访问swagger API

[http://127.0.0.1:8080/sb-demo/swagger-ui.html](##)
