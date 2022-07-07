---
layout: default
title: Java数据库相关
nav_order: 90
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

```java
/**
 * @author 10545
 * @date 2022/6/30 22:18
 */
public class StatementDemo {
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
            //获取执行sql的对象
            statement = conn.createStatement();

            //执行新增语句
            int count = statement.executeUpdate("INSERT INTO products VALUES ('AA',1004,'coffee',2.50,'describe')");
            System.out.println("添加返回结果：" + count);
            
            //执行修改语句
            count = statement.executeUpdate("UPDATE products set prod_price = 2.0 where prod_id = 'AA'");
            System.out.println("修改返回结果："+count);

            //执行删除语句
            count = statement.executeUpdate("DELETE FROM products where prod_id = 'AA'");
            System.out.println("删除返回结果："+count);

            //执行DDL语句：删除表
            count = statement.executeUpdate("DROP TABLE IF EXISTS student");
            System.out.println("删除表返回结果："+count);

            //执行DDL语句：建表
            count = statement.executeUpdate("CREATE TABLE student (id int,name varchar(20))");
            System.out.println("建表返回结果："+count);
            
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

## ResultSet接口

结果集对象，`Statement`对象执行executeQuery语句的返回结果

```java
/**
 * @author 10545
 * @date 2022/7/1 21:47
 */
public class ResultSetDemo {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/database_learning",
                    "root",
                    "12345678");
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM products");
            while (resultSet.next()){
                String column1 = resultSet.getString(1);
                System.out.print(column1+" ");

                int column2 = resultSet.getInt(2);
                System.out.print(column2+" ");

                String column3 = resultSet.getString("prod_name");
                System.out.println(column3+" ");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
```

## PreparedStatement接口

继承`Statement`接口。执行动态sql(预编译sql)

可以防止sql注入

```sql
# sql注入举例说明
# 当password写成等于'a' or 'a' = 'a'，结果会查询出所有用户
SELECT * FROM user WHERE name = 'zhangsan' and password = 'a' or 'a' = 'a';
```

```java
/**
 * @author luguosong
 * @date 2022/7/3
 */
public class PrepareStatementDemo {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/java_database",
                    "root",
                    "12345678");
            //静态sql
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM user WHERE name='张三' AND password='a' OR 'a' = 'a'");
            System.out.println("静态sql查询结果：" + resultSet.next());
            //通过preparedStatement查询
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE name=? AND password=?");
            preparedStatement.setString(1, "张三");
            preparedStatement.setString(2, "'a' OR 'a' = 'a'");
            ResultSet resultSet1 = preparedStatement.executeQuery();
            System.out.println("动态sql查询结果：" + resultSet1.next());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
```

# JDBC工具类

把一些重复代码封装成工具类

```properties
url=jdbc:mysql://localhost:3306/database_learning
user=root
password=12345678
```

```java
/**
 * JDBC工具类
 *
 * @author luguosong
 * @date 2022/7/2
 */
public class JDBCUtils {

    private static String url;
    private static String user;
    private static String password;

    static {
        try {
            Properties properties = new Properties();
            properties.load(new FileReader(JDBCUtils.class.getClassLoader().getResource("jdbc.properties").getPath()));
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取链接
     *
     * @return
     */
    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

    public static void close(Statement stmt, Connection conn) {
        if (stmt != null) {
            try {
                stmt.close();
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

    public static void close(ResultSet resultSet, Statement stmt, Connection conn) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        close(stmt, conn);
    }
}
```

```java
/**
 * @author luguosong
 * @date 2022/7/2
 */
public class Demo {
    public static void main(String[] args) {
        try {
            Connection connection = JDBCUtils.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM products");
            while (resultSet.next()) {
                String column1 = resultSet.getString(1);
                System.out.print(column1 + " ");

                int column2 = resultSet.getInt(2);
                System.out.print(column2 + " ");

                String column3 = resultSet.getString("prod_name");
                System.out.println(column3 + " ");
            }
            JDBCUtils.close(resultSet, statement, connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
```

