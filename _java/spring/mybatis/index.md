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

[中文文档](https://mybatis.org/mybatis-3/zh/index.html)

# MyBatis核心组件

- `SqlSessionFactoryBuilder`(构造器)：它会根据配置或者代码来生成SqlSessionFactory，采用分步构建的Builder模式。
- `SqlSessionFactory`(工厂接口)：依靠它来生成SqlSession，使用的是`工厂模式`
- `SqlSession`(会话)
- `SQL Mapper`(映射器)

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202110091642870.png)

# MyBatis配置文件详解

`SqlSessionFactoryBuilder`可以通过MyBatis配置文件创建`SqlSessionFactory`对象

**总览：**

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration  PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
<!--配置-->
<configuration>
    <!--属性-->
    <properties/>
    <!--设置-->
    <settings/>
    <!--属性命名-->
    <typeAliases/>
    <!--属性处理器-->
    <typeHandlers/>
    <!--对象工厂-->
    <objectFactory  type=""/>
    <!--插件-->
    <plugins/>
    <!--配置环境-->
    <environments default="">
        <!--环境变量-->
        <environment id="">
            <!--事务管理器-->
            <transactionManager type=""/>
            <!--数据源-->
            <dataSource type=""/>
        </environment>
    </environments>
    <!--数据库厂商标识-->
    <databaseIdProvider type=""/>
    <!--映射器-->
    <mappers/>
</configuration>
```


# SqlSessionFactoryBuilder

**作用：**用于创建`SqlSessionFactory`

**生命周期：**只存在于创建`SqlSessionFactory`的方法，不要让其长期存在

# SqlSessionFactory

每一个MyBatis应用都是以一个`SqlSessionFactory`的实例为中心的。

`SqlSessionFactory`的唯一作用：生产`MyBatis`的核心接口对象`SqlSession`，可以认为是一个数据库连接池

**生命周期：**存在于整个MyBatis应用中，一旦创建，长期保存

**`SqlSessionFactory`是一个接口，它有两个实现类：**
- `DefaultSqlSessionFactor`
- `SqlSessionManager`:使用在多线程环境中，具体实现依靠`DefaultSqlSessionFactor`

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202110091650091.png)

## 创建SqlSessionFactory

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

# SqlSession

`SqlSession`是MyBatis的核心接口。相当于Connection对象

**作用(`类似JDBC的Connection对象`)：**
- 获取Mapper接口
- 发送SQL给数据库
- 控制数据库事务

**生命周期：**存活在一个业务请求中，处理完请求，应该关闭这条连接，让它归还SqlSessionFactory

**SqlSession有两个实现类：**
- DefaultSqlSession:单线程使用
- SqlSessionManager：多线程环境下使用

## 创建SqlSession

创建SqlSession对象，并设置事务：

```java
class Demo {
    public static void main(String[] args) {
        SqlSession sqlSession = null;
        try {
            //读取xml
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            //通过SqlSessionFactoryBuilder创建SqlSessionFactory
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            
            
            //创建SqlSession对象
            sqlSession = sqlSessionFactory.openSession();
            
            
            //some code ...
            //提交事务
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            //回滚事务
            sqlSession.rollback();
        } finally {
            if (sqlSession != null) {
                //关闭资源
                sqlSession.close();
            }
        }
    }
}
```

- SqlSession只是一个门面接口，真正干活的是Executor。
- 因为SqlSession对象代表一个资源，使用后要及时关闭。不关闭的话数据库的连接资源就会很快被耗光。

# 映射器Mapper

映射器的主要作用是将SQL查询结果映射为一个POJO，或者将POJO的数据插入到数据库中。代表一个请求中的业务处理

**生命周期：**由`SqlSession`创建，所以生命周期小于等于`SqlSession`。在一个请求中，一旦处理完相关业务就应该废弃它。

定义一个POJO：

```java
package cn.com.lgs.pojo;

import lombok.Data;

/**
 * @author luguosong
 * @date 2021/10/9 17:16
 */
@Data
public class People {
    private int id;
    private String name;
    private int age;
}
```

## 创建映射器

- 定义一个映射接口

```java
package cn.com.lgs.mapper;

import cn.com.lgs.pojo.People;

/**
 * 定义一个映射器接口
 * @author luguosong
 * @date 2021/10/11 11:01
 */
public interface PeopleMapper {
    /**
     * 根据id获取用户
     * @param id
     * @return
     */
    public People getPeople(int id);
}
```

- xml实现映射器

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="cn.com.lgs.mapper.PeopleMapper">
    <select id="getPeople" parameterType="int" resultType="cn.com.lgs.pojo.People">
        select * from people where id = #{id}
    </select>
</mapper>
```



## 配置映射器到Mybatis中

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

    <!--将映射器配置到mybatis-->
    <mappers>
        <!--将xml实现的映射器配置到mybatis-->
        <mapper resource="mapper/PeopleMapper.xml"/>
        <!--将注解实现的映射器配置到mybatis-->
        <mapper class="cn.com.lgs.mapper.PeopleMapperByAnnotation"/>
    </mappers>
</configuration>
```

## 发送SQL

```java
class Demo {
    public static void main(String[] args) {
        SqlSession sqlSession = null;
        try {
            //读取xml
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            //通过SqlSessionFactoryBuilder创建SqlSessionFactory
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            //创建SqlSession对象
            sqlSession = sqlSessionFactory.openSession();


            //使用Mapper发送SQL
            PeopleMapper mapper1 = sqlSession.getMapper(PeopleMapper.class);
            People people1 = mapper1.getPeople(1);
            System.out.println(people1);

            PeopleMapperByAnnotation mapper2 = sqlSession.getMapper(PeopleMapperByAnnotation.class);
            People people2 = mapper2.getPeopleByAnnotation(2);
            System.out.println(people2);

            //提交事务
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            //回滚事务
            sqlSession.rollback();
        } finally {
            if (sqlSession != null) {
                //关闭资源
                sqlSession.close();
            }
        }
    }
}
```

# 其它方法创建组件

以上示例都采用的是主流的做法，获取SqlSessionFactory、实现映射器、将映射器配置到Mybatis、发送SQL都有第二种实现方法。一般都是用上面的主流方法，以下方法不常用。两种做法写在一起我觉得会很混乱，单独整理出来。

`学习的时候只掌握上面主流的方法即可，两种方法一起学容易晕掉`

## 使用代码构建SqlSessionFactory

`缺点：`代码冗长，修改配置需要重新编译才能继续

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

## 使用注解实现映射器

`缺点：`对于稍微复杂一点的语句，Java注解不仅力不从心，还会让你本就复杂的 SQL 语句更加混乱不堪。 因此，如果你需要做一些很复杂的操作，`最好用XML来映射语句`。

```java
package cn.com.lgs.mapper;

import cn.com.lgs.pojo.People;
import org.apache.ibatis.annotations.Select;

/**
 * @author luguosong
 * @date 2021/10/11 14:40
 */
public interface PeopleMapperByAnnotation {

    /**
     * 根据id获取用户
     * @param id
     * @return
     */
    @Select("select * from people where id=#{id}")
    public People getPeopleByAnnotation(int id);
}
```

## 代码方式配置映射器到Mybatis

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
        JdbcTransactionFactory jdbcTransactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", jdbcTransactionFactory, pooledDataSource);
        //创建Configuration对象
        Configuration configuration = new Configuration(environment);


        //配置注解映射器
        configuration.addMapper(PeopleMapperByAnnotation.class);


        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        // 获取SqlSession对象执行sql
        // ...
    }
}
```

## SqlSession发送SQL

```java
class Demo {
    public static void main(String[] args) {
        SqlSession sqlSession = null;
        try {
            //读取xml
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            //通过SqlSessionFactoryBuilder创建SqlSessionFactory
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            //创建SqlSession对象
            sqlSession = sqlSessionFactory.openSession();


            //使用SqlSession发送SQL
            //xml映射器
            People people1 = (People) sqlSession.selectOne("getPeople", 1);
            System.out.println(people1);
            //注解映射器
            Object people2 = sqlSession.selectOne("getPeopleByAnnotation", 2);
            System.out.println(people2);


            //提交事务
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            //回滚事务
            sqlSession.rollback();
        } finally {
            if (sqlSession != null) {
                //关闭资源
                sqlSession.close();
            }
        }
    }
}
```

# 生命周期图示

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202110111753679.png)










