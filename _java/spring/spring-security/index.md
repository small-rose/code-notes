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

```java
private UserCache userCache=new NullUserCache();
```

- `hideUserNotFoundExceptions属性`:标识是否隐藏用户名查找失败异常。与密码错误一样，统一报`BadCredentialsException`异常

```java
protected boolean hideUserNotFoundExceptions = true;
```

- `forcePrincipalAsString属性`:表示是否将`Principal`对象当成字符串来处理。
通过`Authentication`中的`principal`属性可以获取`UserDetails`对象。如果`forcePrincipalAsString`为true,
则`principal`返回的是登录用户名，而不是用户对象

```java
private boolean forcePrincipalAsString = false;
```

- `preAuthenticationChecks属性`:用户状态检查。认证过程中检查账户是否被锁定，账户是否可用，账户是否过期等

```java
private UserDetailsChecker preAuthenticationChecks = new DefaultPreAuthenticationChecks();
```

- `postAuthenticationChecks属性`:负责在密码校验成功后，检查密码是否过期

```java
private UserDetailsChecker postAuthenticationChecks = new DefaultPostAuthenticationChecks();
```

- `additionalAuthenticationChecks抽象方法`：校验密码。具体实现在`DaoAuthenticationProvider`中

```java
protected abstract void additionalAuthenticationChecks(UserDetails userDetails,
		UsernamePasswordAuthenticationToken authentication)
		throws AuthenticationException;
```

- `authenticate方法`:核心校验方法。
  - 首先从登录数据中获取`用户名`，根据用户名去`缓存`中查询用户对象
  - 如果`缓存`中查不到对象,则根据用户名调用`retrieveUser`方法从数据库加载用户，不存在则抛出异常
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
- 
