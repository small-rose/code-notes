---
layout: docs
title: Spring Security
nav_order: 400
parent: Spring相关
---
# 版本说明

Spring Boot：`2.3.2.RELEASE`

Spring Security 版本：`5.3.3.RELEASE`

# 参考资料

[官方文档](https://docs.spring.io/spring-security/site/docs/5.3.3.RELEASE/reference/html5/)

[中文文档](http://docs.jcohy.com/docs/spring-security/5.3.0.RELEASE/html5/zh-cn)

参考书：[深入浅出Spring Security](https://book.douban.com/subject/35383505/)

[博客教程](http://www.javaboy.org/springsecurity/)

# 概述

核心功能：

- 认证
  - 表单认证
  - OAuth2.0认证
  - SAML2.0认证
  - CAS认证
  - RememberMe自动认证
  - JAAS认证
  - OpenID去中心化认证
  - Pre-Authentication Scenarios认证
  - X509认证
  - HTTP Basic认证
  - HTTP Digest认证
- 授权
  - 基于URL的请求授权
  - 支持方法访问授权
  - 支持SpEL访问控制
  - 支持域对象安全（ACL）
  - 支持动态权限配置
  - 支持RBAC权限模型

# 入门案例

## 步骤

spring boot下使用spring security。只需要引入依赖，即可使用。

- 创建`spring-security-hello`工程
- pom文件引入依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <parent>
    <version>2.3.2.RELEASE</version>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
  </parent>
  <modelVersion>4.0.0</modelVersion>

  <groupId>org.example</groupId>
  <artifactId>spring-security-hello</artifactId>
  <version>1.0-SNAPSHOT</version>

  <properties>
    <maven.compiler.source>8</maven.compiler.source>
    <maven.compiler.target>8</maven.compiler.target>
  </properties>

  <dependencies>
    <!--spring security-->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
  </dependencies>

</project>
```

- 编写启动类

```java
package cn.com.lgs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author luguosong
 * @date 2021/11/9 9:30
 */
@SpringBootApplication
public class SpringSecurityHello {
  public static void main(String[] args) {
    SpringApplication.run(SpringSecurityHello.class, args);
  }
}
```

- 编写测试Controller

```java
package cn.com.lgs.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luguosong
 * @date 2021/11/9 9:31
 */
@RestController
public class HelloController {
  @GetMapping("/hello")
  public String hello() {
    return "hello spring security";
  }
}
```

## 运行测试

- 访问`http://localhost:8080/hello`,页面重定向到`http://localhost:8080/login`

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20211113143737.png)

- 默认用户名为`user`,默认密码在控制台打印。输入用户名密码可以访问Controller

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20211113143843.png)

## 自动配置

我们只是引入了依赖，其实`spring boot`帮我们做了很多默认配置。

使用Spring Security，其实就是将这些默认配置改成我们实际需要的配置。 比如用户名密码默认是存储在内存中的，我们得改成从数据库中读取。 比如默认提供了登录表单，我们需要修改成我们自己的表单页面。等等

# 认证流程分析

整体架构如下：

![](img/authentication-process.svg)

## Authentication接口

用户的认证信息主要由`Authentication`的实现类来保存

```java
public interface Authentication extends Principal, Serializable {
  //用来获取用户的权限
  Collection<? extends GrantedAuthority> getAuthorities();

  //用来获取用户凭证，一般来说就是密码。
  Object getCredentials();

  //用来获取用户携带的详细信息，可能是当前请求之类等。
  Object getDetails();

  //用来获取当前用户，例如是一个用户名或者一个用户对象。
  Object getPrincipal();

  //用来获取当前用户，例如是一个用户名或者一个用户对象。
  boolean isAuthenticated();

  void setAuthenticated(boolean isAuthenticated);
}
```

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202111151436083.png)

## AuthenticationManager接口

Spring Security中的认证工作主要由`AuthenticationManager`接口来负责

`AuthenticationManager`在认证成功后，会返回一个`Authentication`对象， 这个`Authentication`对象会被设置到`SecurityContextHolder`中。

如果开发者不想用Spring Security提供的一套认证机制，那么也可以自定义认证流程， 认证成功后，手动将`Authentication`存入`SecurityContextHolder`中。

```java
public interface AuthenticationManager {
  //返回Authentication，表示认证成功。
  //抛出AuthenticationException异常，表示用户输入了无效的凭证。
  //返回null，表示不能断定。
  Authentication authenticate(Authentication authentication)
          throws AuthenticationException;
}
```

## ProviderManager

`ProviderManager`是`AuthenticationManager`的一个重要实现类。

它和`AuthenticationProvider`之间是聚合关系

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202111190929116.png)

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20211118230212.png)

系统可能支持不同的认证方式，一个完整的认证流程可能由多个`AuthenticationProvider`提供。在`ProviderManager`
中遍历每一个`AuthenticationProvider`去执行身份认证，最终得到认证结果

`ProviderManager`本身也可以配置一个`AuthenticationManager`作为parent。这样当`ProviderManager`认证
失败后。会进入parent中再次认证

- `Authentication authenticate(Authentication authentication)`方法
  - 获取authentication对象类型
  - 定义当前认证过程抛出的异常
  - 定义parent中认证时抛出的异常
  - 定义当前认证结果以及parent中认证结果对应的变量。
  - `getProviders()`方法获取并遍历所有`AuthenticationProvider`对象，进行身份验证
    - 判断当前`AuthenticationProvider`是否支持`Authentication`对象,不支持则跳到下一个
    - 调用`AuthenticationProvider`的`authenticate`方法进行身份验证
      - 如果认证成功，调用`copyDetails`给`authentication`设置`details`
      - 如果异常，则通过`lastException`记录异常
  - 遍历结束处理
    - result为空，认证失败，parent不为空
      - 调用parent的authenticate进行验证
      - 出现异常，将异常赋值给`lastException`
    - result不为空
      - 将result中的凭证擦除，防止泄露
      - 同时将登录成功的事件发布出去（发布登录成功事件需要`parentResult`为null）
      - 返回result，后面步骤不执行了
    - result为空，如果`lastException`为空，说明`parent`为null
      - 构造`ProviderNotFoundException`赋值给`lastException`
    - 如果`parentException`为null
      - 发布认证失败
    - 最后抛出`lastException`异常



## AuthenticationProvider接口

`AuthenticationManager`的实现类`ProviderManager`类中管理着一批`AuthenticationProvider`对象数组。 这样，`AuthenticationManager`
提供最终的认证用户，真正干活的其实是`AuthenticationProvider`接口的实现类。

```java
package org.springframework.security.authentication;

public interface AuthenticationProvider {
  ////执行具体的身份认证。
  Authentication authenticate(Authentication authentication)
          throws AuthenticationException;
  //判断当前AuthenticationProvider是否支持对应的身份类型。
  boolean supports(Class<?> authentication);
}
```

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202111161548004.png)

### AbstractUserDetailsAuthenticationProvider抽象类

`AbstractUserDetailsAuthenticationProvider`抽象实现了`AuthenticationProvider`接口

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202111181450209.png)

- `userCache属性`:用户缓存对象，默认情况下没有启用缓存对象
- `hideUserNotFoundExceptions属性`:标识是否隐藏用户名查找失败异常。与密码错误一样，统一报`BadCredentialsException`异常
- `forcePrincipalAsString属性`:表示是否将`Principal`对象当成字符串来处理。
通过`Authentication`中的`principal`属性可以获取`UserDetails`对象。如果`forcePrincipalAsString`为true,
则`principal`返回的是登录用户名，而不是用户对象
- `preAuthenticationChecks属性`:用户状态检查。认证过程中检查账户是否被锁定，账户是否可用，账户是否过期等
- `postAuthenticationChecks属性`:负责在密码校验成功后，检查密码是否过期
- `additionalAuthenticationChecks抽象方法`：校验密码。具体实现在`DaoAuthenticationProvider`中
- `authenticate方法`:核心校验方法。
  - 首先从登录数据中获取`用户名`，根据用户名去`缓存`中查询用户对象
  - 如果`缓存`中查不到对象,则根据用户名调用`retrieveUser`方法从`UserDetailsService`加载用户，不存在则抛出异常
  - 拿到用户对象后，调用`preAuthenticationChecks.check`方法进行用户状态检查。
  - 然后调用`additionalAuthenticationChecks`方法进行密码校验操作。
  - 然后调用`postAuthenticationChecks.check`方法检验密码是否过期
  - 所有步骤都顺利完成后，调用createSuccessAuthentication方法创建一个
  认证后的`UsernamePasswordAuthenticationToken`（Authentication接口的实现类）对象并返回，认证后的对象包含了认证主体、凭证和角色等信息

#### DaoAuthenticationProvider类

继承了`AbstractUserDetailsAuthenticationProvider`抽象类

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202111181458942.png)

- `USER_NOT_FOUND_PASSWORD常量`:当用户查询失败时的默认密码，值为:`userNotFoundPassword`
- `passwordEncoder属性`:密码加密对比工具
- `userNotFoundEncodedPassword属性`:保存默认密码加密后的值
- `userDetailsService属性`:用户查找工具
- `userDetailsPasswordService属性`：提供密码修改服务
- 构造方法中默认指定`passwordEncoder属性`为`PasswordEncoder`对象。开发者也可以使用set方法自定义`passwordEncoder属性`
- `additionalAuthenticationChecks方法`:进行密码校验。
  - 第一个参数userDetails是从数据库查询出来的用户对象
  - 第二个参数authentication则是用户输入的参数
  - 两个参数分别提取出用户名密码，然后调用`passwordEncoder.matches`进行比对
- `retrieveUser方法`:获取用户对象。
  - 方法一开始会先调用`prepareTimingAttackProtection();`
    - 该方法使用`passwordEncoder`对常量`USER_NOT_FOUND_PASSWORD`进行加密，将加密结果保存在`userNotFoundEncodedPassword`
    - 当查找不到用户时抛出异常，会调用`mitigateAgainstTimingAttack`方法进行密码比对。这个比对注定是失败的。
    - `目的`:防止`旁道攻击`。如果用户不存在就直接抛出异常而不进行密码比对。黑客通过大量测定通过耗费时间获取系统信息。
  - 调用`userDetailsService.loadUserByUsername`方法去数据库查询
- `createSuccessAuthentication方法`:登录成功后创建一个全新的`UsernamePasswordAuthenticationToken`。
同时判断是否需要进行密码升级，如果需要进行密码升级，就会在该方法中进行加密方案升级

## AbstractAuthenticationProcessingFilter过滤器

将认证组件与登录关联起来，处理任何提交给它的身份认证。

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202111220846016.png)

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202111220847691.png)

`AbstractAuthenticationProcessingFilter`是一个抽象类。如果使用`用户名密码`方式登录，那么它对应的实现类是
`UsernamePasswordAuthenticationFilter`，构造出来的`Authentication`对象则是
`UsernamePasswordAuthenticationToken`

AbstractAuthenticationProcessingFilter代码：
- `requiresAuthentication方法`判断是不是登录认证请求
  - 是认证请求：执行下面的认证代码
  - 不是认证请求：继续走完剩余的过滤器
- `attemptAuthentication方法`获取一个认证后的`Authentication对象`
  - `attemptAuthentication方法`是一个抽象方法，具体实现在`UsernamePasswordAuthenticationFilter`中
- 认证成功后，通过`sessionStrategy.onAuthentication`处理session并发问题
- `continueChainBeforeSuccessfulAuthentication属性`表示认证成功后过滤器是否要继续往下执行，默认为false
- `unsuccessfulAuthentication方法`处理认证失败
  - 从`SecurityContextHolder`中清除数据
  - 清楚Cookie等信息
  - 调用认证失败回调方法
- `successfulAuthentication方法`处理认证成功
  - 向`SecurityContextHolder`中存入用户信息
  - 处理Cookie
  - 发布成功事件，事件类型是`InteractiveAuthenticationSuccessEvent`
  - 调用认证成功回调方法


UsernamePasswordAuthenticationFilter代码：
- 声明用户名密码字段
  - 用户名字段默认为：username
  - 密码字段默认为：password
- 默认只处理`/login`的登录请求
- `attemptAuthentication方法`
  - 首先确认是post请求
  - 通过`obtainUsername方法`和`obtainPassword方法`提取用户名密码
  - 构建`UsernamePasswordAuthenticationToken`对象
  - 调用`getAuthenticationManager().authenticate`执行认证操作

## 认证自定义配置示例

### 配置多个数据源

配置多个`AuthenticationProvider`,并为不同的`AuthenticationProvider`提供不同的`UserDetailService`即可

这里为了方便使用`InMemoryUserSetailsManager`提供`UserDetailsService`实例。
实际开发中一般使用`自定义UserDetailsService`查询数据库

`ProviderManager`中创建两个`AuthenticationProvider`，两个`AuthenticationProvider`分别使用不同的数据源

示例工程：[spring-security-multiuser](https://github.com/guosonglu/code-notes/tree/master/_java/spring/spring-security/spring-security-multiuser)

主要配置类代码如下：

```java
package cn.com.lgs.config;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    @Primary
    UserDetailsService us1() {
        return new InMemoryUserDetailsManager(
                User.builder().username("zhangsan").password("{noop}123").roles("admin").build());
    }

    @Bean
    UserDetailsService us2() {
        return new InMemoryUserDetailsManager(
                User.builder().username("lisi").password("{noop}1234").roles("user").build());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() {
        DaoAuthenticationProvider dao1 = new DaoAuthenticationProvider();
        dao1.setUserDetailsService(us1());
        DaoAuthenticationProvider dao2 = new DaoAuthenticationProvider();
        dao2.setUserDetailsService(us2());
        ProviderManager providerManager = new ProviderManager(dao1, dao2);
        return providerManager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/test")
                .usernameParameter("uname")
                .passwordParameter("passwd")
                .permitAll()
                .and()
                .csrf().disable();
    }
}
```

### 添加登录验证码

两种思路：
- 自定义过滤器
- 自定义认证逻辑

这里介绍如何自定义认证逻辑实现登录验证码功能

- 引入依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <version>2.3.2.RELEASE</version>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
    </parent>

    <groupId>org.example</groupId>
    <artifactId>spring-security-kaptcha</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>

    <dependencies>
        <!--spring security-->
        <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
      <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
      </dependency>
      <!--验证码-->
      <dependency>
        <groupId>com.github.penggle</groupId>
        <artifactId>kaptcha</artifactId>
        <version>2.3.2</version>
      </dependency>
    </dependencies>

</project>
```

- 编写验证码配置类

```java
package cn.com.lgs.config;

/**
 * @author luguosong
 * @date 2021/11/22 18:09
 */
@Configuration
public class KaptchaConfig {

  @Bean
  Producer kaptcha() {
    Properties properties = new Properties();
    properties.setProperty("kaptcha.image.width", "150");
    properties.setProperty("kaptcha.image.height", "50");
    properties.setProperty("kaptcha.textproducer.char.string", "0123456789");
    properties.setProperty("kaptcha.textproducer.char.length", "4");
    Config config = new Config(properties);
    DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
    defaultKaptcha.setConfig(config);
    return defaultKaptcha;
  }
}
```

- 编写controller

- /vc.jpg
  - 生成验证码文本，并存储到session中
  - 根据验证码文本生成图片，并通过io流写出到前端
- /index
  - 登录成功页

```java
package cn.com.lgs.controller;

/**
 * @author luguosong
 * @date 2021/11/23 9:56
 */
@RestController
public class LoginController {
  @Autowired
  Producer producer;

  @GetMapping("/vc.jpg")
  public void getVerifyCode(HttpServletResponse resp, HttpSession session) throws IOException {
    resp.setContentType("image/jpeg");
    String text = producer.createText();
    session.setAttribute("kaptcha", text);
    BufferedImage image = producer.createImage(text);
    try (ServletOutputStream out = resp.getOutputStream()) {
      ImageIO.write(image, "jpg", out);
    }
  }

  @RequestMapping("/index")
  public String index() {
    return "login success";
  }
}
```

- 自定义登录页

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
  <meta charset="UTF-8">
  <title>登录</title>
  <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
  <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
  <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<style>
  #login .container #login-row #login-column #login-box {
    border: 1px solid #9C9C9C;
    background-color: #EAEAEA;
  }
</style>
<body>
<div id="login">
  <div class="container">
    <div id="login-row" class="row justify-content-center align-items-center">
      <div id="login-column" class="col-md-6">
        <div id="login-box" class="col-md-12">
          <form id="login-form" class="form" action="/doLogin" method="post">
            <h3 class="text-center text-info">登录</h3>
            <div th:text="${SPRING_SECURITY_LAST_EXCEPTION}"></div>
            <div class="form-group">
              <label for="username" class="text-info">用户名:</label><br>
              <input type="text" name="uname" id="username" class="form-control">
            </div>
            <div class="form-group">
              <label for="password" class="text-info">密码:</label><br>
              <input type="text" name="passwd" id="password" class="form-control">
            </div>
            <div class="form-group">
              <label for="kaptcha" class="text-info">验证码:</label><br>
              <input type="text" name="kaptcha" id="kaptcha" class="form-control">
              <img src="/vc.jpg" alt="">
            </div>
            <div class="form-group">
              <input type="submit" name="submit" class="btn btn-info btn-md" value="登录">
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
```

- 编写`AuthenticationProvider`实现类继承`DaoAuthenticationProvider`类，重写`authenticate`认证方法。 自定义认证逻辑

```java
package cn.com.lgs.config;

/**
 * @author luguosong
 */
public class KaptchaAuthenticationProvider extends DaoAuthenticationProvider {

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    String kaptcha = req.getParameter("kaptcha");
    String sessionKaptcha = (String) req.getSession().getAttribute("kaptcha");
    if (kaptcha != null && sessionKaptcha != null && kaptcha.equalsIgnoreCase(sessionKaptcha)) {
      return super.authenticate(authentication);
    }
    throw new AuthenticationServiceException("验证码输入错误");
  }
}
```

- 编写Spring Security配置类，配置`AuthenticationManager对象`和`AuthenticationProvider对象`。

注意：在configure方法中给验证码接口配置放行

```java
package cn.com.lgs.config;

/**
 * @author luguosong
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Bean
  AuthenticationProvider kaptchaAuthenticationProvider() {
    InMemoryUserDetailsManager users = new InMemoryUserDetailsManager(User.builder()
            .username("zhangsan").password("{noop}123").roles("admin").build());
    KaptchaAuthenticationProvider provider = new KaptchaAuthenticationProvider();
    provider.setUserDetailsService(users);
    return provider;
  }

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    ProviderManager manager = new ProviderManager(kaptchaAuthenticationProvider());
    return manager;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
            .antMatchers("/vc.jpg").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/mylogin.html")
            .loginProcessingUrl("/doLogin")
            .defaultSuccessUrl("/index.html")
            .failureForwardUrl("/mylogin.html")
            .successForwardUrl("/index")
            .usernameParameter("uname")
            .passwordParameter("passwd")
            .permitAll()
            .and()
            .csrf().disable();
  }
}
```

- 编写Spring Boot启动类

```java
package cn.com.lgs;

/**
 * 自定义认证逻辑添加验证码功能
 *
 * @author luguosong
 * @date 2021/11/23 10:23
 */
@SpringBootApplication
public class SpringSecurityKaptchaApplication {
  public static void main(String[] args) {
    SpringApplication.run(SpringSecurityKaptchaApplication.class, args);
  }
}
```

# 过滤器链分析

## ObjectPostProcessor接口

对象后置处理器。

如果一个对象创建成功后，如果还有一些额外的事情需要补充，那么可以通过`ObjectPostProcessor`进行补充

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202111231342664.png)

ObjectPostProcessor有两个实现类：

- AutowireBeanFactoryObjectPostProcessor实现类
  - 一个对象new出来后，只要调用`AutowireBeanFactoryObjectPostProcessor#postProcess`方法。就可以注入到spring容器中。
    原理是调用spring容器中的`AutowireCapableBeanFactory`对象将new出来的对象注入到Spring容器中
- CompositeObjectPostProcessor实现类
  - 维护一个List集合，存放一堆`ObjectPostProcessor`后置处理器对象

在Spring Security中，开发者可以灵活的配置过滤器，一旦选定过滤器，每一个`过滤器`都会有一个对应的`配置器`， 叫做`xxxConfigurer`,过滤器是在配置器中new出来的,然后在后置处理器`postProcess`
方法中处理一遍。

## SecurityFilterChain接口

过滤器链对象

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202111231427212.png)

- matches
  - 判断request请求是否应该被当前过滤器链处理
- getFilters
  - 如果上面matches返回ture，则getFilters返回过滤器集合

`SecurityFilterChain`的默认实现类为`DefaultSecurityFilterChain`

一个项目中，`SecurityFilterChain`的实例可能会有多个

## SecurityBuilder接口

作用：`构建对象`

所有需要构建的对象都可以通过`SecurityBuilder`构建

`默认的过滤器链`、`代理过滤器`、`AuthenticationManager`等都可以使用SecurityBuilder构建。

```java
public interface SecurityBuilder<O> {
  O build() throws Exception;
}
```

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202111231515491.png)

### HttpSecurityBuilder

`HttpSecurityBuilder`继承自`SecurityBuilder`接口，指定了`SecurityBuilder`的泛型为`DefaultSecurityFilterChain`

也就是说`HttpSecurityBuilder`最终要构建的对象是`DefaultSecurityFilterChain`

- `getConfigurer方法`：获取一个配置器
- `removeConfigurer方法`：移除一个配置器
- `setSharedObject方法`：设置一个可以在多个配置器之间共享的对象
- `getSharedObject方法`：获取一个可以在多个配置器之间共享的对象
- `authenticationProvider方法`：配置一个认证器AuthenticationProvider
- `userDetailsService方法`:配置一个数据源
- `addFilterAfter方法`：某个过滤器之后添加一个自定义过滤器
- `addFilterBefore方法`:某个过滤器之前添加一个自定义过滤器
- `addFilter方法`:添加一个Spring Security自带的过滤器

### AbstractSecurityBuilder

实现build方法，`确保build方法只执行一次。`

- `AtomicBoolean building变量`：确保即使在多线程环境下，配置也只构建一次
- `build方法`
  - 通过`building`变量控制配置类只构建一次，具体的构建工作交给`doBuild方法`完成（工厂方法设计模式）
  - 设置为final,子类不能再次重写`build方法`
- `getObject方法`：返回构建对象
- `doBuild方法`：具体的构建方法，具体实现在其子类中（工厂方法设计模式）

### AbstractConfiguredSecurityBuilder

继承自`AbstractSecurityBuilder`

- `BuildState枚举类`：描述构建过程中的不同状态
  - UNBUILT：配置类构建前
  - INITIALIZING：初始化中
  - CONFIGURING：配置中
  - BUILDING：构建中
  - BUILT：构建完成
  - `isInitializing方法`：是否正在初始化中
  - `isConfigured方法`：是否配置完成
- `configurers变量`：保存所有配置类
- `allowConfigurersOfSameType字段`：是否允许相同类型的配置类
- `apply方法`：添加配置类，具体添加过程调用`add方法`
- `add方法`：添加配置类
- `getConfigurers(Class<C> clazz)方法`:从`configurers变量`中返回某个配置类对应的所有实例
- `getConfigurer方法`:从`configurers变量`中返回某个配置类对应的第一个实例
- `removeConfigurers方法`:移除某一个配置类对应的所有实例，并返回被移除的配置类实例集合
- `removeConfigurer方法`:移除某一个配置类对应的第一个实例，并返回被移除的配置类实例
- `getConfigurers()私有方法`:把所有配置类实例放到一个集合中返回。在配置类`初始化`和`配置`的时候，会调用该方法
- `doBuild方法`：核心构建方法（工厂方法设计模式）
  - `beforeInit方法`：空方法，如果想要在初始化前做一些准备工作，可以通过重写该方法实现。
  - `init方法`：初始化方法，遍历所有配置类，并调用其init方法完成初始化操作
  - `beforeConfigure方法`：空方法，如果想在configure方法之前做一些准备工作，可以通过重写该方法实现
  - `configure方法`：完成所有配置类的配置




