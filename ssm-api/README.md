# 基于SpringBoot2+MyBatis+MySQL的CRUD 

#### 0. 建表sql
```sql
    DROP TABLE IF EXISTS `user`;
    CREATE TABLE `user`  (
    `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户唯一id--主键',
    `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名--不能重复',
    `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户昵称',
    `user_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
    `user_mail` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户邮箱',
    `user_state` int(11) NOT NULL COMMENT '用户状态 0 封禁 1正常',
    `user_reward` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户打赏码图片路径',
    `user_avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户头像',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`user_id`) USING BTREE
    ) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

    -- ----------------------------
    -- Records of user
    -- ----------------------------
    INSERT INTO `user` VALUES (1, 'admin', '白小白', '123456', '1819939081@qq.com', 1, '1', 'https://note.youdao.com/yws/api/personal/file/74F8E7CAED244D0F86F0866F10267C34?method=download&shareKey=f2cb9362abc0b7613b3d1ec07c46311e', NOW(), NULL);
    INSERT INTO `user` VALUES (2, 'biubiu', '一蓑烟雨任平生', '123456', 'testadmin@163.com', 1, '1', 'https://i.picsum.photos/id/1005/300/300.jpg', NOW(), NULL);

    -- ----------------------------
    -- 添加列
    -- ----------------------------
    -- ALTER TABLE USER ADD COLUMN my_time TIMESTAMP DEFAULT NULL COMMENT '时间'; --

```


#### 1. pom.xml
```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
        <modelVersion>4.0.0</modelVersion>
        
        <groupId>com.biu</groupId>
        <artifactId>myapi</artifactId>
        <version>0.0.1-SNAPSHOT</version>	
        
        <parent>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-parent</artifactId>
            <version>2.1.3.RELEASE</version>
        </parent>
        
        <properties>
            <java.version>1.8</java.version>
        </properties>
        
        <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-web</artifactId>
            </dependency>
            <dependency>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
                <version>1.18.12</version>
            </dependency>
        </dependencies>

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

#### 2. 组件，依赖
##### 2.1 mysql+mybatis 依赖
```xml
    <!--mybatis-->
    <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
        <version>2.1.2</version>
    </dependency>
    <!--mysql-->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.20</version>
    </dependency>
```

##### 2.2 配置
###### 数据库连接配置
```properties
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.url=jdbc:mysql://localhost:3306/testdb?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf-8&autoReconnect=true
    spring.datasource.username=root
    spring.datasource.password=root
```
###### mapper.xml扫描配置
`mybatis.mapper-locations=classpath:mapper/*Mapper.xml`

###### 控制台打印sql
`logging.level.com.biu.myapi.mapper=debug`

###### mapper包扫描 
`@MapperScan("com.biu.myapi.mapper")`

##### 2.3 增删改查sql
```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
            "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
    <mapper namespace="com.biu.myapi.mapper.UserMapper">

        <resultMap id="UserRMap" type="com.biu.myapi.entity.User">
            <id property="id" column="user_id"/>
            <result property="username" column="user_name"/>
            <result property="nickname" column="nick_name"/>
            <result property="password" column="user_password"/>
            <result property="mail" column="user_mail"/>
            <result property="state" column="user_state"/>
            <result property="reward" column="user_reward"/>
            <result property="avatar" column="user_avatar"/>
            <result property="createTime" column="create_time"/>
            <result property="updateTime" column="update_time"/>
        </resultMap>

        <sql id="all_column">
            user_id,user_name,nick_name,user_password,user_mail,user_state,user_reward,user_avatar,create_time,update_time
        </sql>

        <select id="findAll" resultMap="UserRMap">
            SELECT
            <include refid="all_column"/>
            FROM
            user
            WHERE user_state = 1
            ORDER BY create_time DESC
        </select>

        <select id="findById" resultMap="UserRMap" parameterType="integer">
            SELECT
            <include refid="all_column"/>
            FROM
            user
            WHERE user_id = #{value}
            AND user_state = 1
        </select>

        <select id="find" resultMap="UserRMap" parameterType="com.biu.myapi.entity.User">
            SELECT
            <include refid="all_column"/>
            FROM
            user
            <where>
                <if test="username!=null and username != ''">
                    user_name = #{username}
                </if>
                <if test="nickname!=null">
                    and nick_name LIKE concat('%' ,#{nickname}, '%')
                </if>
                <if test="mail!=null">
                    and user_mail = #{mail}
                </if>
                and user_state = 1
            </where>
        </select>

        <delete id="delete" parameterType="integer">
            DELETE FROM user WHERE user_id = #{value}
        </delete>

        <update id="remove" parameterType="integer">
            UPDATE user SET user_state = 0 WHERE user_id = #{id}
        </update>

        <update id="save" parameterType="com.biu.myapi.entity.User" useGeneratedKeys="true" keyProperty="id">
            INSERT INTO user (user_id,user_name,nick_name,user_password,user_mail,user_state,user_reward,user_avatar,create_time,update_time)
            VALUES (null, #{username}, #{nickname}, #{password}, #{mail}, #{state}, #{reward}, #{avatar}, NOW(), NULL);
        </update>

        <update id="update" parameterType="com.biu.myapi.entity.User">
            UPDATE user
            <set>
                <if test="username!=null">user_name = #{username},</if>
                <if test="nickname!=null">nick_name = #{nickname},</if>
                <if test="password!=null">user_password = #{password},</if>
                <if test="mail!=null">user_mail = #{mail},</if>
                <if test="reward!=null">user_reward = #{reward},</if>
                <if test="avatar!=null">user_avatar = #{avatar},</if>
                update_time = NOW()
            </set>
            WHERE user_id = #{id}
        </update>
    </mapper>
```

#### 3. controller restful风格接口

```java
@RestController
@RequestMapping("/api-info/users")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/{id}")
    public RestResponse getUserById(@PathVariable Integer id) {
        User user = userService.findById(id);
        if (user == null) {
            return RestResponse.failure("查询失败");
        } else {
            return RestResponse.success("查询成功").setData(user);
        }
    }

    @GetMapping
    public Map<String, Object> list(QueryUserRequest request) {
        List<User> userList = null;
        if (request == null) {
            userList = userService.findAll();
        } else {
            userList = userService.find(ParamHelper.converter(request));
        }
        if (CollectionUtils.isEmpty(userList)) {
            return RestResponse.failure("查询失败");
        } else {
            return RestResponse.success("查询成功").setData(userList);
        }
    }

    @Delete("/{id}")
    public Map<String, Object> remove(@PathVariable("id") Integer id, @RequestParam("db") Boolean db) {
        if (id == null) {
            return RestResponse.failure("id为必传参数");
        }

        if (db != null && db) {
            userService.delete(id);
        } else {
            userService.remove(id);
        }
        return RestResponse.success("删除成功");
    }

    @PostMapping
    public Map<String, Object> save(@RequestBody AddUserRequest request) {
        User user = userService.save(ParamHelper.converter(request));
        if (user != null) {
            return RestResponse.success("添加成功").setData(user);
        } else {
            return RestResponse.failure("添加失败");
        }
    }

    @PutMapping("/{id}")
    public Map<String, Object> update(@RequestBody EditUserRequest request, @PathVariable("id") Integer id) {
        if (id == null && request == null) {
            return RestResponse.failure("id为必传参数");
        }
        User user = userService.update(ParamHelper.converter(request, id));
        if (user != null) {
            return RestResponse.success("修改成功").setData(user);
        } else {
            return RestResponse.failure("修改失败");
        }
    }

}
```


**分页查询**

```sql
# 如果查询user表中的前10条记录，并且是从第1条数据开始查，那么索引0可以省略不写，如下：
SELECT * FROM USER LIMIT 10;
# 从第5条数据开始查，查3条记录（注意：第一条数据索引是0，第二条数据索引是1，以此类推）
SELECT * FROM USER LIMIT 4,3;
SELECT * FROM USER LIMIT 3 OFFSET 4; # MySQL5.0后支持这种语法，结果和上面一样

```
