## Spring Boot集成Shiro

**Shiro的三个核心概念**

- Subject： 代表当前正在执行操作的用户，但Subject代表的可以是人，也可以是任何第三方系统帐号。当然每个subject实例都会被绑定到SercurityManger上。
- SecurityManger:SecurityManager是Shiro核心，主要协调Shiro内部的各种安全组件，这个我们不需要太关注，只需要知道可以设置自定的Realm。
- Realm:用户数据和Shiro数据交互的桥梁。比如需要用户身份认证、权限认证。都是需要通过Realm来读取数据。

## 1. 引入依赖

```java
<!-- Shiro-Spring -->
    <dependency>
        <groupId>org.apache.shiro</groupId>
        <artifactId>shiro-spring</artifactId>
        <version>1.5.1</version>
    </dependency>
```

### 2. 配置文件

- Realm配置文件

  ```java
  public class UserRealm extends AuthorizingRealm {
  
      /** 执行授权操作 */
      @Override
      protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
          return null;
      }
  
      /** 执行认证操作 */
      @Override
      protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
          return null;
      }
  }
  ```

  

- ShiroConfig

  ```java
  @Configuration
  public class ShiroConfig {
  
      
      @Bean
      public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
          ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
          bean.setSecurityManager(securityManager);
  
          /**
           * 设置Shiro的内置过滤器 (有顺序)
           *  常用过滤器：
           *   anon: 无需认证（登录）可以访问
           *   authc: 必须认证才能访问
           *   user: 必须有“记住我”功能才能访问
           *   role: 该资源必须得到角色权限才能访问
           *   perms: 该资源必须得到资源权限才能访问
           */
          Map<String, String> filterMap = new LinkedHashMap<>();
          filterMap.put("/*", "authc");
  
          bean.setFilterChainDefinitionMap(filterMap);
  
          bean.setLoginUrl("/login");
  
          return bean;
      }
  
      @Bean("securityManager")
      public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
          DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
          securityManager.setRealm(userRealm);
          return securityManager;
      }
  
      @Bean
      public UserRealm getRealm() {
          return new UserRealm();
      }
  
  }
  ```

### 3. Shiro Controller类

```java
/**
     * Shiro 登录操作
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录
     */
    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public String loin(String username, String password, Model model) {
        //1 获取Subject对象
        Subject subject = SecurityUtils.getSubject();
        //2 封装用户信息
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //3 执行登录
        try {
            subject.login(token);
            return "/index/#/";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg", "用户名不存在！");
            return "login";
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("msg", "密码错误！");
            return "login";
        }
    }
```

### 注意

1. 配置登录页面 `bean.setLoginUrl("/toLogin");`
2. 配置不拦截`filterMap.put("/codeServlet", "anon");`
3. 拦截资源`filterMap.put("/*", "authc");`