## SpringBoot集成Mybatis和通用mapper

###  1. 导入相关依赖包

```xml
<!--mybatis-->
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>2.1.2</version>
</dependency>
<!--通用mapper启动器-->
<dependency>
    <groupId>tk.mybatis</groupId>
    <artifactId>mapper-spring-boot-starter</artifactId>
    <version>2.1.5</version>
</dependency>
<!--mysql-->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
</dependency>
```

### 2. 配置

```properties
server.port=8888

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/db_web?useSSL=false&useUnicode=true&characterEncoding=utf-8&autoReconnect=true&serverTimezone=Asia/Shanghai
spring.datasource.username=xxxx
spring.datasource.password=xxxx

mybatis.mapper-locations=classpath:mapper/*.xml
logging.level.com.biubiu.web.mapper=debug
```

### 3. 启动类加上包扫描注解

**@MapperScan 是在 tk.mybatis.spring.annotation.MapperScan**

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.biubiu.web.mapper")
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

}
```

### 4. 实体类映射表

```java
@Data
@Table(name = "tb_users")
public class User implements Serializable {
    private static final long serialVersionUID = 3167212910028834735L;

    @Id
    private Integer id;
    private String name;
    private Integer age;
    private Integer sex;
    private String tel;
    private String address;

    private Date createTime;
    @Transient//这个注解表示，查询的时候不查询phone这个字段
    private Date updateTime;
    private Integer valid;
}
```

### 5. Mapper类的实现

```java
import com.biubiu.web.entity.User;
import tk.mybatis.mapper.common.Mapper;

/**
 * UserMapper
 *
 * @author biubiu
 */
public interface UserMapper extends Mapper<User> {
}

```

### 6. 测试就已经ok了

### 7. 拓展，现有方法不满足现有逻辑，自定义sql语句

- a. userMapper新加方法

  ```java
  public interface UserMapper extends Mapper<User> {
  
      List<User> getUserByTel(String tel);
  
      void setUserValid(Integer[] valids);
  }
  ```

- b. xml文档编辑sql

  ```xml
  <?xml version="1.0" encoding="UTF-8" ?>
  <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  <mapper namespace="com.biubiu.web.mapper.UserMapper">
  
      <select id="getUserByTel" resultType="com.biubiu.web.entity.User"
              parameterType="java.lang.String">
  		select
  			*
  		 from tb_users t
  		 where t.tel = #{tel}
  	</select>
  
      <update id="deleteByIds" parameterType="integer">
          update tb_users t
          <set>
              t.valid = 0
          </set>
          where t.id in
          <foreach collection="array" index="index" item="item" open="(" separator="," close=")">
              #{item}
          </foreach>
      </update>
  </mapper>
  ```

- 测试成功



## mybatis分页插件

### 1. 引入依赖

```xml
<dependency>
	<groupId>com.github.pagehelper</groupId>
	<artifactId>pagehelper-spring-boot-starter</artifactId>
	<version>1.2.13</version>
</dependency>
```

### 2. 配置

```properties
pagehelper.helper-dialect=mysql
pagehelper.params=count=countSql
pagehelper.reasonable=true
pagehelper.support-methods-arguments=true
```

### 3. 测试类

```java
@Controller
public class PageController {

    @Resource
    private UserService userService;

    @GetMapping("/index")
    public String test(Model model,
                       @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum, 2);
        List<User> users = userService.queryAllUser();
        PageInfo<User> userPageInfo = new PageInfo<>(users);
        model.addAttribute("pageInfo", userPageInfo);
        return "index";
    }
}
```

### 4. 测试页面

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<div align="center">
    <table border="1">
        <tr>
            <th>id</th>
            <th>name</th>
            <th>sex</th>
            <th>age</th>
        </tr>
        <tr th:each="item:${pageInfo.list}">
            <td th:text="${item.id}"></td>
            <td th:text="${item.name}"></td>
            <td th:text="${item.sex}"></td>
            <td th:text="${item.age}"></td>
        </tr>
    </table>
    <p>当前 <span th:text="${pageInfo.pageNum}"></span> 页,总 <span th:text="${pageInfo.pages}"></span> 页,共 <span th:text="${pageInfo.total}"></span> 条记录</p>
    <a th:href="@{/index}">首页</a>
    <a th:href="@{/index(pageNum=${pageInfo.hasPreviousPage}?${pageInfo.prePage}:1)}">上一页</a>
    <a th:href="@{/index(pageNum=${pageInfo.hasNextPage}?${pageInfo.nextPage}:${pageInfo.pages})}">下一页</a>
    <a th:href="@{/index(pageNum=${pageInfo.pages})}">尾页</a>
</div>
</body>
</html>
```

### 5. 结果展示

![](./img/2020042001.jpg)