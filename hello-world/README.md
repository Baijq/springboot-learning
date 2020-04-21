# 第一篇：构建第一个SpringBoot项目

SpringBoot是一款非常优秀非常流行的java开发框架。官方网址:[https://spring.io/](https://spring.io/)

1. 简化了开发流程

    - [x] SpringBoot以前，我们使用的SSM工程，需要web.xml,spring-web.xml,spring-service.xml,spring-dao.xml,spring-content.xml等一系列xml配置文件

    - [x] SpringBoot 都没有，简化了各种繁琐的xml配置

2. SpringBoot内置Tomcat，做到开箱即用

    都不需要我们将项目打包放到Tomcat里，直接内置使用，就和使用javaSE的main方法一样简单

3. 自动装配（非常强大的Starter）-- 并且提供约束大于配置的理念

    SpringBoot把各种模块全部封装成了一个个的Starter，想用哪个就引用哪个Starter。并且提供简单的配置文件做个性化配置


## 一、SpringBoot 工程创建

本篇会从零开始，搭建SpringBoot工程。

#### 1. 工具/环境推荐

    - JDK 1.8
    - Maven 3.6.0
    - IntelliJ IDEA / STS

#### 2. pom文件

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
        <modelVersion>4.0.0</modelVersion>

        <!-- 项目坐标 -->
        <groupId>com.bjq.demo</groupId>
        <artifactId>sb-demo</artifactId>
        <version>0.0.1-SNAPSHOT</version>
        
        <!-- 父级模块 -->
        <parent>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-parent</artifactId>
            <version>2.2.5.RELEASE</version>
        </parent>

        <!-- 项目属性设置 -->
        <properties>
            <java.version>1.8</java.version>
            <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
            <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        </properties>

        <!-- 项目依赖 -->
        <dependencies>
            <!-- boot-web -->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-web</artifactId>
            </dependency>
        </dependencies>

        <!-- 打包插件 -->
        <build>
            <plugins>
                <plugin>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-maven-plugin</artifactId>
                </plugin>
            </plugins>
        </build>

    </project>
    ```

#### 3. 推荐使用结构

    ```yaml
    sb-demo
        -src
            -main
                -java
                    -com.sb.demo
                        -config
                        -domain
                        -controller
                        -service
                        SbApplication.java
                -resources
                    -static
                    -templates
                    application.properties
            -test
                -java
                -resources
        -pom.xml
    ```

#### 4. 启动类

    ```java
    import org.springframework.boot.SpringApplication;
    import org.springframework.boot.autoconfigure.SpringBootApplication;
    /**
    * WebApplication @SpringBootApplication注解很重要，是整个SpringBoot的核心入口
    *
    * @author biubiu
    */
    @SpringBootApplication
    public class WebApplication {

        public static void main(String[] args) {
            SpringApplication.run(WebApplication.class, args);
        }
    }
    ```

#### 5. 测试类HelloController

    ```java
    /**
     * HelloController
     *
     * @author biubiu
     */
    @RestController
    public class HelloController {
        
        @GetMapping("/hello")
        public String hello() {
            return "Hello World!";
        }
    }
    ```
    
#### 6. 访问 http://127.0.0.1:8080/hello

![biubiu-spring-boot-learning-1-pic1](https://note.youdao.com/yws/api/personal/file/ABA8114DEE84426C9BE8DF11AD7EBCF4?method=download&shareKey=e76abad46980c2704d5b8daac8f2a261)


## 二、SpringBoot 总结说明
>1.SpringBoot项目的核心机制：
- [X] pom.xml
    
    描述了SpringBoot所有依赖的jar包以及版本号。
    和普通maven的pom文件的区别是有一个父工程parent的存在。


- [X] SpringBootStartApplication.java

    SpringBoot项目的入口就在这个类里，有一个main方法。直接右键Run As 运行，和一般的JavaSE没啥区别。

- [X] @SpringBootApplication

    这个注解是一个复合注解，里面包括三个关键注解
    `@SpringBootConfiguration`
    `@EnableAutoConfiguration`
    `@ComponentScan`