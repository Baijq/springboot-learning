SpringBoot 整合[Apollo](https://github.com/ctripcorp/apollo/wiki) 配置中心

1. 导入jar包：
```xml
<!--apollo-->
<dependency>
    <groupId>com.ctrip.framework.apollo</groupId>
    <artifactId>apollo-client</artifactId>
    <version>1.1.0</version>
</dependency>
```
2. 配置apollo必要参数   
SpringBoot的话最简单的一种是直接在application.properties里配置
- 必要参数
    - appId
     ```properties
     app.id=YOUR_APP_ID
     ```
    - meta Server
     ```properties
     apollo.meta=http://config-server-url/
     ```
- 其他参数（可选）
    - 缓存地址（默认在c://opt/data//...） 
    - 环境 (DEV/PRO/UAT/TEST/LOCAL)
    - 其他
    
    也可以配置在启动参数里：
    ```bash
        -Dapollo.meta=http://10.29.204.82:8080 
        -Denv=Local 
        -Dspring.profile.active=dev 
        -Dlogging.config=classpath:logback-spring-dev.xml
    ```

3. 获取配置

   - 调用API方式，最简单，最灵活

     ```java
     Config config = ConfigService.getConfig("AppVersion");
     String someKey = "AndroidMessage";
     String someDefaultValue = "none";
     String value = config.getProperty(someKey, someDefaultValue);
     ```

   - Java配置方式

     ```java
     @EnableApolloConfig(value = "AppVersion") //AppVersion指定了namespace
     @Configuration
     public class ApolloConfig {
     
         @Value("${IOSVerCode}")
         public String iosVerCode;
     
     }
     ```