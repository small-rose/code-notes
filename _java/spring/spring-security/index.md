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

我们只是引入了依赖，其实spring boot帮我们做了很多默认配置，如下：

- 启用 Spring Security 的默认配置,该配置会创建 springSecurityFilterChain ,然后加入到 Servlet 过滤器链中. 此bean负责应用程序内的所有安全性(
  保护应用程序URL,验证提交的用户名和密码,重定向到登录表单等) .
- 创建一个 `UserDetailsService` bean,其中包含用户名 user 和随机生成的密码,该密码将记录到控制台.
- 针对每个请求,使用Servlet容器向 `springSecurityFilterChain` 注册过滤器.

使用Spring Security，其实就是将这些默认配置改成我们实际需要的配置。 
比如用户名密码默认是存储在内存中的，我们得改成从数据库中读取。 
比如默认提供了登录表单，我们需要修改成我们自己的表单页面。等等

# 整体架构

搞清整体架构很重要，否则后面容易乱掉

## 认证

用户的认证信息主要由`Authentication`的实现类来保存：

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

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/Authentication.png)

Spring Security中的认证工作主要由`AuthenticationManager`接口来负责:

```java
public interface AuthenticationManager {
  //返回Authentication，表示认证成功。
  //抛出AuthenticationException异常，表示用户输入了无效的凭证。
  //返回null，表示不能断定。
  Authentication authenticate(Authentication authentication)
          throws AuthenticationException;
}
```

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/AuthenticationManager.png)

`AuthenticationManager`最主要的实现类是`ProviderManager`。
`ProviderManager`管理众多的`AuthenticationProvider`实例:

```java
private List<AuthenticationProvider> providers = Collections.emptyList();
```

`AuthenticationProvider`的实现类处理不同的身份验证：

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/AuthenticationProvider.png)

总结一下：
- Authentication：保存认证信息
- AuthenticationManager：负责认证
- ProviderManager：认证具体实现
- AuthenticationProvider：它的实现类处理各种类型的认证


## 授权

`AccessDecisionManager`接口决定访问是否允许被通过：

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/AccessDecisionManager.png)

`AccessDecisionManager`的实现类管理着一批`AccessDecisionVoter`。
`AccessDecisionVoter`是一个投票器，检查用户是否具备应有的角色。

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/AccessDecisionVoter.png)

在Spring Security中，用户请求一个资源（通常是一个网络接口或者一个Java方法）所需要的角色会被封装成一个`ConfigAttribute`对象，
在`ConfigAttribute`中只有一个`getAttribute`方法，该方法返回一个String字符串，就是角色的名称。
一般来说，角色名称都带有一个ROLE_前缀，投票器`AccessDecisionVoter`所做的事情，
其实就是比较用户所具备的角色和请求某个资源所需的`ConfigAttribute`之间的关系。

## 过滤器

Spring Security中的认证和授权基于Servlet过滤器完成的，
下面是Spring Security中常见的过滤器。第三列默认加载表示不进行任何配置，默认加载的过滤器：

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20211114164051.png)

可以看到，`UsernamePasswordAuthenticationFilter`过滤器默认是开着的，这对应这前面入门案例的表单登录

这些过滤器按照既定优先级排列，最终形成一个过滤器链。过滤器并不是直接放在Web原生过滤器链中的，
而是通过`FilterChainProxy`进行管理。`FilterChainProxy`通过Spring框架提供的`DelegatingFilterProxy`整合到Web原生过滤器链中。

当系统中存在多个不同的认证体系时，会使用多个多滤器链。
`FilterChainProxy`先匹配上哪个过滤器链，就使用哪个过滤器链处理。

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20211114195501.png)


## 登录数据保存

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20211114210007.png)

登录成功后，登录成功的用户信息保存在`SecurityContextHolder`中。
登录完毕后，`SecurityContextHolder`中的数据会保存到Session中。
`SecurityContextHolder`会被清空

以后每次再发送请求，会先从Session中获取登录数据，保存到`SecurityContextHolder`中。
在请求结束时，`SecurityContextHolder`中的数据会被保存到Session,然后清空`SecurityContextHolder`。

## 获取用户

Spring Security中定义了`UserDetails`规范用户定义。

`UserDetailsService`接口负责提供用户数据：

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/UserDetailsService.png)

Spring Security 默认使用`InMemoryUserDetailsManager`提供用户。

一般开发者自定义`UserDetailsService`的实现提供用户。

# 自动化配置类

Spring Boot之所以能坐到零配置使用Spring Security,就是因为它提供了众多的自动化配置类

## UserDetailsServiceAutoConfiguration



# 表单认证


