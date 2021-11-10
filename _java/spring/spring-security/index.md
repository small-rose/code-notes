---
layout: docs
title: Spring Security
nav_order: 400
parent: Spring相关
---
# 概述

基于Spring Boot：`2.3.2.RELEASE`
Spring Security 版本：`5.3.3.RELEASE`

Spring Security的版本并不需要亲自指定，[父工程](#父工程)继承自`spring-boot-starter-parent`，`spring-boot-starter-parent`中已经帮我们做好了版本管理

[官方文档](https://docs.spring.io/spring-security/site/docs/5.3.3.RELEASE/reference/html5/)

[中文文档](http://docs.jcohy.com/docs/spring-security/5.3.0.RELEASE/html5/zh-cn)

参考书：[深入浅出Spring Security](https://book.douban.com/subject/35383505/)

视频教程：[尚硅谷SpringSecurity框架教程](https://www.bilibili.com/video/BV15a411A7kP?from=search&seid=17934619456497753665&spm_id_from=333.337.0.0)

阅读本篇文章，建议结合源代码仔细分析。只看文章很容易乱掉。

# 过滤器

在Spring Security中，认证、授权等功能都是基于过滤器来完成的。

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202111081800610.png)

过滤器并不是直接放在Web项目的原生过滤器链中，而是通过一个`FilterChainProxy`来统一管理。
Spring Security中的过滤器链通过`FilterChainProxy`嵌入到Web项目的原生过滤器链中。

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202111081801770.png)

在Spring Security中，这样的过滤器链可能有多个。当存在多个过滤器链时，多个过滤器链之间要指定优先级，
当请求到达后，会从`FilterChainProxy`进行分发，先和哪个过滤器链匹配上，就用哪个过滤器链进行处理。
当系统中存在多个不同的认证体系时，那么使用多个过滤器链就非常有效。

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202111081805124.png)

`FilterChainProxy`通过Spring框架提供的`DelegatingFilterProxy`整合到原生过滤器链中。

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202111081806459.png)

# 认证

## 快速入门

- 创建名为`spring-security-hello`的Spring Boot项目

- pom文件如下

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

- Spring Boot启动类

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

- Controller

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

- 运行项目，访问`/hello`,系统会跳转到登录认证界面

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202111090936106.png)

- 默认用户名为`user`,密码在控制台打印

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202111090942678.png)

- 使用用户名密码登录成功后可以访问'/hello'

## 流程分析

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202111090944966.png)

- 过程分析：
  1. 客户端（浏览器）发起请求去访问`/hello`接口，这个接口默认是需要认证之后才能访问的
  2. 请求经过Spring Security的过滤器链，在`FilterSecurityInterceptor`过滤器中被拦截下来，
  因为系统发现用户未认证。接下来抛出`AccessDeniedException`异常。
  3. `ExceptionTranslationFilter`过滤器捕获`AccessDeniedException`异常，
  并调用`LoginUrlAuthenticationEntryPoint#commence`方法给客户端返回`302`，
  要求客户端重定向到`/login`页面。
  4. 客户端发送`/login`请求,`DefaultLoginPageGeneratingFilter`过滤器拦截请求，返回`登录界面`

- 过程中Spring Security背后默默做了什么：
  - 创建一个名为`springSecurityFilterChain`的过滤器，并注入到`Spring容器`中，这个过滤器将负责所有的安全管理，
    包括用户的认证、授权、重定向到登录页面等（`springSecurityFilterChain`实际上代理了Spring Security中的过滤器链）。
  - 创建UserDetailsService实例，负责提供用户数据。
  - 给用户生成一个默认的登录页面
  - 开启CSRF攻击防御
  - 开启会话固定攻击防御
  - 集成X-XSS-Protection
  - 集成X-Frame-Options以防止单击劫持

## UserDetails接口

Spring Security用户对象接口`UserDetails`：

```java
public interface UserDetails extends Serializable {
  //返回当前账户所具备的权限
  Collection<? extends GrantedAuthority> getAuthorities();

  //返回当前账户的密码
  String getPassword();

  //返回当前账户的用户名
  String getUsername();

  //返回当前账户是否未过期
  boolean isAccountNonExpired();

  //返回当前账户是否未锁定
  boolean isAccountNonLocked();

  //返回当前账户凭证（如密码）是否未过期。
  boolean isCredentialsNonExpired();

  //返回当前账户是否可用
  boolean isEnabled();
}
```

## UserDetailsService接口

`UserDetailsService`接口负责提供用户数据源：

```java
public interface UserDetailsService {
  UserDetails loadUserByUsername(String username)
          throws UsernameNotFoundException;
}
```

在实际项目中，一般需要`开发者自定义UserDetailsService的实现`。

当然Spring Security也为`UserDetailsService`提供了默认实现:

![](http://edrawcloudpubliccn.oss-cn-shenzhen.aliyuncs.com/viewer/self/1059758/share/2021-11-9/1636440053/main.svg)

- `UserDetailsManager`在UserDetailsService的基础上，继续定义了添加用户、更新用户、删除用户、修改密码以及判断用户是否存在共5种方法。

- `JdbcDaoImpl`在UserDetailsService的基础上，通过spring-jdbc实现了从数据库中查询用户的方法。

- `InMemoryUserDetailsManager`实现了`UserDetailsManager`中关于用户的增删改查方法，不过都是基于内存的操作，数据并没有持久化。

- `JdbcUserDetailsManager`继承自`JdbcDaoImpl`同时又实现了`UserDetailsManager`接口， 因此可以通过`JdbcUserDetailsManager`
  实现对用户的增删改查操作，这些操作都会持久化到数据库中。 不过`JdbcUserDetailsManager`有一个局限性，就是操作数据库中用户的SQL都是提前写好的，
  不够灵活，因此在实际开发中`JdbcUserDetailsManager`使用并不多。

- `CachingUserDetailsService`的特点是会将UserDetailsService缓存起来。

- `UserDetailsServiceDelegator`则是提供了UserDetailsService的懒加载功能。

- `ReactiveUserDetailsServiceAdapter`是webflux-web-security模块定义的UserDetailsService实现。

## 默认用户配置类

![](http://edrawcloudpubliccn.oss-cn-shenzhen.aliyuncs.com/viewer/self/1059758/share/2021-11-9/1636441865/main.svg)

当我们使用Spring Security时，如果仅仅只是引入一个Spring Security依赖， 则默认使用的用户就是由`InMemoryUserDetailsManager`提供的。

- 满足以下条件，使用默认配置类配置`InMemoryUserDetailsManager`：
  - 应用包含`AuthenticationManager`类
  - Spring容器中没有注册`AuthenticationManager`，`AuthenticationProvider`，`UserDetailsService`

`InMemoryUserDetailsManager`的自动化配置类如下：

```java

package org.springframework.boot.autoconfigure.security.servlet;


@Configuration(proxyBeanMethods = false)
//应用包含`AuthenticationManager`类
@ConditionalOnClass(AuthenticationManager.class)
@ConditionalOnBean(ObjectPostProcessor.class)
//Spring容器中没有注册`AuthenticationManager`，`AuthenticationProvider`，`UserDetailsService`
@ConditionalOnMissingBean(
        value = {AuthenticationManager.class, AuthenticationProvider.class, UserDetailsService.class},
        type = {"org.springframework.security.oauth2.jwt.JwtDecoder",
                "org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector"})
public class UserDetailsServiceAutoConfiguration {

  private static final String NOOP_PASSWORD_PREFIX = "{noop}";

  private static final Pattern PASSWORD_ALGORITHM_PATTERN = Pattern.compile("^\\{.+}.*$");

  private static final Log logger = LogFactory.getLog(UserDetailsServiceAutoConfiguration.class);


  //默认配置类将InMemoryUserDetailsManager对象注册到Spring容器中去
  @Bean
  @ConditionalOnMissingBean(
          type = "org.springframework.security.oauth2.client.registration.ClientRegistrationRepository")
  @Lazy
  public InMemoryUserDetailsManager inMemoryUserDetailsManager(SecurityProperties properties,
                                                               ObjectProvider<PasswordEncoder> passwordEncoder) {
    SecurityProperties.User user = properties.getUser();
    List<String> roles = user.getRoles();
    //将`SecurityProperties`中的`User`对象赋值给`UserDetails`对象
    return new InMemoryUserDetailsManager(User.withUsername(user.getName()).
            password(getOrDeducePassword(user, passwordEncoder.getIfAvailable()))
            .roles(StringUtils.toStringArray(roles)).build());
  }

  private String getOrDeducePassword(SecurityProperties.User user, PasswordEncoder encoder) {
    String password = user.getPassword();
    if (user.isPasswordGenerated()) {
      logger.info(String.format("%n%nUsing generated security password: %s%n", user.getPassword()));
    }
    if (encoder != null || PASSWORD_ALGORITHM_PATTERN.matcher(password).matches()) {
      return password;
    }
    return NOOP_PASSWORD_PREFIX + password;
  }

}
```

在默认配置类中，`inMemoryUserDetailsManager`方法从`SecurityProperties`获取User对象：

```java

@ConfigurationProperties(prefix = "spring.security")
public class SecurityProperties {
  private User user = new User();

  public User getUser() {
    return this.user;
  }

  public static class User {
    private String name = "user";
    private String password = UUID.randomUUID().toString();
    private List<String> roles = new ArrayList<>();
    //省略getter/setter
  }
}
```

User类中，我们就可以看到默认的用户名是`user`，默认的密码是一个`UUID字符串`。

创建`InMemoryUserDetailsManager`对象用的并不是`SecurityProperties`中的`User`对象， 而是实现`UserDetails`接口的`User`对象。将`SecurityProperties`
中的`User`对象赋值给`UserDetails`对象。

当然，我们可以在项目配置文件`application.yml`中对`SecurityProperties`中的`User`对象进行修改:

```yaml
spring:
  security:
    user:
      name: zhangsan
      password: 123
      roles: admin,user
```

经过上述配置，用户名变为`zhangsan`，密码变为`123`

## 默认页面原理

`DefaultLoginPageGeneratingFilter`过滤器用于生成默认登录界面,下面是部分代码:

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202111091518760.png)

- 在doFilter方法中，首先判断出当前请求是否为登录出错请求、注销成功请求或者登录请求。 如果是这三种请求中的任意一个，就会在`DefaultLoginPageGeneratingFilter`过滤器中生成登录页面并返回，
  否则请求继续往下走，执行下一个过滤器

这里重点搞明白为什么/hello请求没有被拦截，而/login请求却被拦截了，其他都很好懂。

`DefaultLogoutPageGeneratingFilter`过滤器会先判断是否是注销请求，则渲染一个注销请求的页面返回给客户端， 渲染过程和前面登录页面的渲染过程类似，部分核心源码如下：

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202111091524590.png)

## 自定义表单登录

自定义配置类进行修改：

```java
//自定义配置类修改默认的登录表单
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests() //表示开启权限配置
            .anyRequest().authenticated() //表示所有的请求都要认证之后才能访问
            .and() //相当于又回到HttpSecurity实例，重新开启新一轮的配置
            .formLogin() //开启表单登录配置
            .loginPage("/login.html") //用来配置登录页面地址
            .loginProcessingUrl("/doLogin") //用来配置登录接口地址
            .defaultSuccessUrl("/index") //登录成功后的跳转地址
            .failureUrl("/login.html") //登录失败后的跳转地址
            .usernameParameter("uname") //表示登录用户名的参数名称
            .passwordParameter("passwd") //表示登录密码的参数名称
            .permitAll() //表示跟登录相关的页面和接口不做拦截
            .and() //相当于又回到HttpSecurity实例，重新开启新一轮的配置
            .csrf().disable(); //表示禁用CSRF防御功能
  }
}
```

示例工程：[spring-security-form-login](https://github.com/guosonglu/code-notes/tree/master/_java/spring/spring-security/spring-security-form-login)
这里不展开说了，主要就是配置类

## 表单登录成功处理

配置类中除了`defaultSuccessUrl`方法可以实现登录成功后的跳转。`successForwardUrl`也可以实现登录后的跳转。

区别：
- defaultSuccessUrl
  - 用户在未认证的情况下，访问了`/hello`页面，登录后访问`/hello`页面。
  - 如果用户一开始访问的是登录页面，则登录后重定向到`successForwardUrl`所指的界面
  - defaultSuccessUrl有一个重载方法，第二个参数传true。像这样`defaultSuccessUrl("/index",true)`。
  会使效果与`successForwardUrl`相同。即不考虑用户之前的访问地址，只要登录成功，
  就重定向到`defaultSuccessUrl`所指定的页面。
  - 通过重定向实现的跳转（客户端跳转）
- successForwardUrl
  - 不管登录前访问的什么界面。只要用户登录成功，就会通过服务器端跳转到`successForwardUrl`所指定的页面。
  - 通过服务器端跳转

无论是`defaultSuccessUrl`还是`successForwardUrl`，最终配置的都是`AuthenticationSuccessHandler`。

`AuthenticationSuccessHandler`接口专门用来处理登录成功的事项

AuthenticationSuccessHandler接口共有三个实现类,如下图：

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202111091802189.png)

- `SimpleUrlAuthenticationSuccessHandler`类继承自`AbstractAuthenticationTargetUrlRequestHandler`, 通过其中的handle方法实现重定向
- `SavedRequestAwareAuthenticationSuccessHandler`在`SimpleUrlAuthenticationSuccessHandler`的基础上 增加了请求缓存的功能
- `ForwardAuthenticationSuccessHandler`的实现则比较容易，就是一个服务端跳转。

## 表单登录失败处理

无论是`failureUrl`还是`failureForwardUrl`，最终所配置的都是`AuthenticationFailureHandler`接口的实现。 Spring
Security中提供了`AuthenticationFailureHandler`接口，用来规范登录失败的实现：

```java
public interface AuthenticationFailureHandler {
  void onAuthenticationFailure(HttpServletRequest request,
                               HttpServletResponse response,
                               AuthenticationException exception)
          throws IOException, ServletException;
}
```

`AuthenticationFailureHandler`接口有以下五类实现：

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202111101020360.png)

- `SimpleUrlAuthenticationFailureHandler`
  默认的处理逻辑就是通过重定向跳转到登录页面，当然也可以通过配置forwardToDestination属性将重定向改为服务器端跳转，failureUrl方法的底层实现逻辑就是SimpleUrlAuthenticationFailureHandler。

- `ExceptionMappingAuthenticationFailureHandler`可以实现根据不同的异常类型，映射到不同的路径。

- `ForwardAuthenticationFailureHandler`
  表示通过服务器端跳转来重新回到登录页面，failureForwardUrl方法的底层实现逻辑就是ForwardAuthenticationFailureHandler。

- `AuthenticationEntryPointFailureHandler`是Spring Security 5.2新引进的处理类，可以通过AuthenticationEntryPoint来处理登录异常。

- `DelegatingAuthenticationFailureHandler`可以实现为不同的异常类型配置不同的登录失败处理回调。

## 定制注销页面

```java

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            //省略其他配置
            .and()
            .logout()
            .logoutUrl("/logout")
            .invalidateHttpSession(true)
            .clearAuthentication(true)
            .logoutSuccessUrl("/mylogin.html")
            .and()
            .csrf().disable();
  }
}
```

- 通过.logout()方法开启注销登录配置。
- logoutUrl指定了注销登录请求地址，默认是GET请求，路径为/logout。
- invalidateHttpSession表示是否使session失效，默认为true。
- clearAuthentication表示是否清除认证信息，默认为true。
- logoutSuccessUrl表示注销登录后的跳转地址。

## 登录用户数据获取



# 授权
