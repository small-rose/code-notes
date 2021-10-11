---
layout: docs
title: MyBatis
nav_order: 1000
parent: Spring相关
---

# 概述

功能：访问数据库

**_特点：_**
- 不屏蔽SQL
- 提供强大灵活的映射机制
- 提供了使用Mapper的接口编程

案例版本：`3.5.7`

# MyBatis核心组件

- `SqlSessionFactoryBuilder`(构造器)：它会根据配置或者代码来生成SqlSessionFactory，采用分步构建的Builder模式。
- `SqlSessionFactory`(工厂接口)：依靠它来生成SqlSession，使用的是`工厂模式`
- `SqlSession`(会话)
- `SQL Mapper`(映射器)

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202110091642870.png)

# SqlSessionFactory

每一个MyBatis应用都是以一个`SqlSessionFactory`的实例为中心的。

`SqlSessionFactory`的唯一作用：生产`MyBatis`的核心接口对象`SqlSession`

**`SqlSessionFactory`是一个接口，它有两个实现类：**
- `DefaultSqlSessionFactor`
- `SqlSessionManager`:使用在多线程环境中，具体实现依靠`DefaultSqlSessionFactor`

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202110091650091.png)

## 使用XML构建SqlSessionFactory

- 在`resources`目录下创建`mybatis-config.xml`文件

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration  PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"></transactionManager>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/code_notes"/>
                <property name="username" value="root"/>
                <property name="password" value="12345678"/>
            </dataSource>
        </environment>
    </environments>
</configuration>
```

- 通过xml文件创建SqlSessionFactory对象

```java
class Demo {
    public static void main(String[] args) {
        try {
            //读取xml
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            //通过SqlSessionFactoryBuilder创建SqlSessionFactory
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            System.out.println(sqlSessionFactory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

## 使用代码构建SqlSessionFactory

```java
class Demo {
    public static void main(String[] args) {
        //数据库链接池信息
        PooledDataSource pooledDataSource = new PooledDataSource();
        pooledDataSource.setDriver("com.mysql.jdbc.Driver");
        pooledDataSource.setUrl("jdbc:mysql://localhost:3306/code_notes");
        pooledDataSource.setUsername("root");
        pooledDataSource.setPassword("12345678");
        pooledDataSource.setDefaultAutoCommit(false);
        //采用MyBatis的JDBC事务方式
        JdbcTransactionFactory jdbcTransactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", jdbcTransactionFactory, pooledDataSource);
        //创建Configuration对象
        Configuration configuration = new Configuration(environment);
        //构建SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        System.out.println(sqlSessionFactory);
    }
}
```

# SqlSession

MyBatis的核心接口。

**作用(`类似JDBC的Connection对象`)：**
- 获取Mapper接口
- 发送SQL给数据库
- 控制数据库事务

**SqlSession有两个实现类：**
- DefaultSqlSession:单线程使用
- SqlSessionManager：多线程环境下使用







