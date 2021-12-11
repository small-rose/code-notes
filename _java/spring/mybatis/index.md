---
layout: docs
title: mybatis
nav_order: 200
parent: Spring相关
---

# 概述

[官方文档](https://mybatis.org/mybatis-3/zh/index.html#)

## 什么是MyBatis

MyBatis 是一款优秀的持久层框架，它支持自定义 SQL、存储过程以及高级映射。
MyBatis免除了几乎所有的JDBC代码以及设置参数和获取结果集的工作。
MyBatis可以通过简单的 XML 或注解来配置和映射原始类型、接口和
Java POJO（Plain Old Java Objects，普通老式 Java 对象）为数据库中的记录。


# MyBatis-Plus

[官方文档](https://baomidou.com/guide/)

MyBatis-Plus（简称MP）是一个MyBatis(opensnewwindow)的增强工具，在MyBatis的基础上只做增强不做改变，为简化开发、提高效率而生。

## 快速入门

- 创建`user表`

<hb></hb>
```sql
/*
 Navicat Premium Data Transfer

 Source Server         : 本地mysql
 Source Server Type    : MySQL
 Source Server Version : 50540
 Source Host           : localhost:3306
 Source Schema         : mybatis_plus

 Target Server Type    : MySQL
 Target Server Version : 50540
 File Encoding         : 65001

 Date: 10/12/2021 16:52:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'Jone', 18, 'test1@baomidou.com');
INSERT INTO `user` VALUES (2, 'Jack', 20, 'test2@baomidou.com');
INSERT INTO `user` VALUES (3, 'Tom', 28, 'test3@baomidou.com');
INSERT INTO `user` VALUES (4, 'Sandy', 21, 'test4@baomidou.com');
INSERT INTO `user` VALUES (5, 'Billie', 24, 'test5@baomidou.com');

SET FOREIGN_KEY_CHECKS = 1;
```
<he></he>

- 创建springboot工程`mybatis-plus-hello`

- 引入依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.3.2.RELEASE</version>
        <relativePath/>
    </parent>

    <groupId>org.example</groupId>
    <artifactId>mybatis-plus-hello</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.4.3</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>
    </dependencies>
</project>
```

- 编写配置文件
  - 注意：这里`url`属性后面要加`useSSL=false&serverTimezone=UTC`参数，否则可能报错

```yaml
# DataSource Config
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/mybatis_plus?useUnicode=yes&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC
    username: root
    password: 12345678
```

- 创建实体类

```java
package cn.com.lgs.domain;

import lombok.Data;

/**
 * @author luguosong
 * @date 2021/12/10 16:02
 */
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
```

- 创建mapper接口

```java
package cn.com.lgs.mapper;

import cn.com.lgs.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @author luguosong
 * @date 2021/12/10 16:03
 */

public interface UserMapper extends BaseMapper<User> {
}
```

- 编写启动类
  - 启动类中扫描mapper接口所在包

```java
package cn.com.lgs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author luguosong
 * @date 2021/12/10 16:01
 */
@SpringBootApplication
@MapperScan("cn.com.lgs.mapper")
public class MybatisPlusHelloApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusHelloApplication.class,args);
    }
}
```

- 编写测试类

```java
package cn.com.lgs.test;

import cn.com.lgs.domain.User;
import cn.com.lgs.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author luguosong
 * @date 2021/12/10 16:04
 */
@SpringBootTest
public class SampleTest {
    @Resource
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }
}
```
