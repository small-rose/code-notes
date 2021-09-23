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

# 代码演示

## 父工程

### 工程创建

- 创建`spring-security`模块，继承自`spring-boot-starter-parent`,pom.xml如下：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <packaging>pom</packaging>
    <modules>
        <module>spring-security-hello</module>
    </modules>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.3.2.RELEASE</version>
        <relativePath/>
    </parent>

    <groupId>org.example</groupId>
    <artifactId>spring-security</artifactId>
    <version>1.0-SNAPSHOT</version>

</project>
```

## HelloWord工程

### 工程创建

- 创建`spring-security-hello`模块，继承自[父工程](#父工程)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>spring-security</artifactId>
        <groupId>org.example</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>spring-security-hello</artifactId>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
    </dependencies>
</project>
```

- 创建入口类

```java
package cn.com.lgs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSecurityHelloApplication {
  public static void main(String[] args) {
    SpringApplication.run(SpringSecurityHelloApplication.class,args);
  }
}
```

- 编写测试Controller

```java
package cn.com.lgs.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

  @RequestMapping("/hello")
  public String hello() {
    return "hello security";
  }
}
```

### 运行测试

- 启动`spring-security-hello`项目
- 访问`http://localhost:8080/test/hello`,页面会转到`http://localhost:8080/login`

![](https://raw.githubusercontent.com/guosonglu/images/master/blog-img/202109230908900.png)

- 默认用户名为：`user`,密码会在控制台显示

![](https://raw.githubusercontent.com/guosonglu/images/master/blog-img/202109230909889.png)

- 通过默认提供的用户名和密码，可以成功访问到Controller

## 自定义用户名密码

上面Hello world程序中，默认密码为`user`,默认密码为控制台自动生成的。现在我们想要使用自己的密码

### 方法一

### 方法二

### 方法三