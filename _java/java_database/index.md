---
layout: default
title: Java数据库相关
nav_order: 6
---

# JDBC(Java Database Connectivity)

JDBC是Java定义的一套操作所有关系型数据库的接口，不同的数据库厂商提供接口的实现类（数据库驱动）。

## 入门案例

- 导入数据库驱动
- 注册驱动
- 获取数据库的连接对象Connection
- 定义sql语句
- 获取执行sql语句的对象Statement
- 执行sql，接收返回的结果
- 处理结果
- 释放资源

```xml

<dependencies>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.29</version>
    </dependency>
</dependencies>
```

```java
/**
 * JDBC测试
 *
 * @author 10545
 * @date 2022/6/1 21:49
 */
public class JDBCDemo {
  /**
   * @param args
   */
  public static void main(String[] args) {
    Connection conn = null;
    Statement statement = null;
    try {
      //1.注册驱动：mysql-connector-java 版本5以后会自动注册驱动
      //2.获取数据库连接
      conn = DriverManager.getConnection(
              "jdbc:mysql://localhost:3306/database_learning",
              "root",
              "12345678");
      //定义sql语句
      String sql = "UPDATE products SET prod_price =10.01 WHERE prod_id = 'FB'";
      //获取执行sql的对象
      statement = conn.createStatement();
      //执行sql
      int count = statement.executeUpdate(sql);
      //打印结果
      System.out.println(count);

    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      //释放资源
      if (statement != null) {
        try {
          statement.close();
        } catch (SQLException e) {
          throw new RuntimeException(e);
        }
      }
      if (conn != null) {
        try {
          conn.close();
        } catch (SQLException e) {
          throw new RuntimeException(e);
        }
      }

    }
  }
}
```

```text
1
```

## DriverManager类

进行驱动管理类

- 功能
  - 注册驱动
  - 获取数据库连接

## Connection接口

数据库连接

- 功能
  - 获取数据库执行对象
    - Statement createStatement()
    - PreparedStatement prepareStatement(String sql)
  - 管理事务
    - 开启事务：void setAutoCommit(boolean autoCommit)
    - 提交事务：void commit()
    - 回滚事务：void rollback()

## Statement接口

执行静态sql

## ResultSet接口

结果集对象

## PreparedStatement接口

继承`Statement`接口。执行动态sql

