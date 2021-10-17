---
layout: docs 
title: 服务端开发 
nav_order: 4 
parent: JavaSE
---

中间件、服务器端或 web 应用程序开发重要的技能

# JDBC

## 概述

是一个Java API可以访问任何类型的表格数据，尤其是存储在关系数据库中的数据。

JDBC 功能：

- 连接到数据源
- 向数据库发送更新和查询语句
- 处理从数据库接收的结果

## HelloWorld

简单示例：

```java
class Demo {
    public static void main(String[] args) {
        try {
            //在高版本的JDK，已经不需要手动调用class.forName方法了
            //Class.forName(“com.mysql.jdbc.Driver”);
            // DriverManager对象用于连接到数据库驱动程序并登录到数据库
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/code_notes",
                    "root",
                    "12345678");
            //Statement 对象将SQL语言查询传递到数据库
            Statement stmt = null;
            stmt = con.createStatement();
            // ResultSet 对象用于检索查询结果
            ResultSet rs = stmt.executeQuery("SELECT * FROM people");
            //执行一个简单的 while 循环，检索并显示这些结果
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

## JDBC组件

- The JDBC API

`JDBC API`提供对来自Java编程语言的关系数据的编程访问。使用`JDBC API`，应用程序可以执行SQL语句、检索结果并将更改传播回底层数据源。 `JDBC API`还可以与分布式异构环境中的多个数据源交互。

- JDBC Driver Manager

`JDBC DriverManager`类定义了可以将 Java 应用程序连接到 JDBC 驱动程序的对象。 DriverManager 历来是 JDBC 架构的支柱。 它非常小而简单。

标准扩展包 javax.naming 和 javax.sql 允许您使用通过 Java Naming and Directory Interface (JNDI) 命名服务注册的 DataSource 对象来建立与数据源的连接。
您可以使用任一连接机制，但建议尽可能使用 DataSource 对象。

- JDBC Test Suite

帮助您确定JDBC驱动程序将运行您的程序。这些测试并不全面或详尽，但它们确实使用了JDBC API中的许多重要特性。

- JDBC-ODBC Bridge

Java 软件桥通过 ODBC 驱动程序提供 JDBC 访问。请注意，您需要将 ODBC 二进制代码加载到使用此驱动程序的每台客户端计算机上。因此，ODBC 驱动程序最适用于客户端安装不是主要问题的公司网络，或者适用于在三层体系结构中用
Java 编写的应用程序服务器代码。

## JDBC架构

JDBC API 支持用于数据库访问的两层和三层处理模型。

### 两层架构

Java applet或者应用程序直接与数据源通讯

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202110121450681.gif)

### 三层架构

命令被发送到服务的中间层，然后服务将命令发送到数据源，数据源处理命令并将结果发送回中间层，中间层随后将结果发给用户

`中间层`可以对数据进行更新和控制，简化了应用程序部署。在许多情况下三层体系结构可以提供性能优势。

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202110121513186.gif)

随着企业越来越多地使用Java编程语言编写服务器代码，`JDBC API越来越多地被用于三层体系结构的中间层`。使JDBC成为服务器技术的一些特性是它对连接池、分布式事务和断开连接的行集的支持。JDBC
api还允许从Java中间层访问数据源。

## 建立链接

### DriverManager类

通过数据源的URL，我们可以建立与指定的数据源的连接。

JDBC4.0之前需要使用`Class.forName(“com.mysql.jdbc.Driver”);`来注册驱动， 新版本JDK在DriverManager中的静态代码块中已经帮我们注册了驱动，当`DriverManager`
类第一次尝试建立连接时， 它会自动加载类路径中找到的任何 JDBC 4.0驱动程序。

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202110121410861.png)

示例：

```java
class Demo {
    public static void main(String[] args) {
        //通过DriverManager获取连接
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/code_notes",
                "root",
                "12345678");
    }
}
```

### DataSource接口

这其实是一个接口，`需要驱动程序自行提供实现类`，`DataSource`接口优于`DriverManager`，因为我们可以更加清楚底层数据源的信息。 通过设置 `DataSource`
的属性可以让其表示指定的数据源，但是不同厂商的驱动程序对该接口的实现存在一定差异，因此具体的设置方法各有所不同。

`优点：`
- DataSource 具有更好的可移植性和维护性：不同于 DriverManage r需要硬编码，通过 JNDI ,DataSource 接口的实现可以通过配置来完成。
- DataSource 可以为分布式事务和数据库连接池提供更好的支持。

#### mysql驱动内部实现

每个数据库驱动内部都有各自的DataSource接口实现类，这里以`mysql-connector-java`为例

```java
class Demo {
    public static void main(String[] args) {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setDatabaseName("code_notes");
        dataSource.setPortNumber(3306);
        dataSource.setUser("root");
        dataSource.setPassword("12345678");
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from people");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

#### C3P0

```java
class Demo {
    public static void main(String[] args) {
        try {
            ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
            //内部注册驱动了，可以省略
            //comboPooledDataSource.setDriverClass("com.mysql.jdbc.Driver");
            comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/code_notes");
            comboPooledDataSource.setUser("root");
            comboPooledDataSource.setPassword("12345678");
            Connection connection = comboPooledDataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from people");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

#### druid

```java
class Demo {
    public static void main(String[] args) {
        try {
            DruidDataSource druidDataSource = new DruidDataSource();
            druidDataSource.setUrl("jdbc:mysql://localhost:3306/code_notes");
            druidDataSource.setUsername("root");
            druidDataSource.setPassword("12345678");
            DruidPooledConnection connection = druidDataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from people");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

创建`DruidDataSource`对象还可以使用`DruidDataSourceFactory`。

## 处理异常

当 JDBC 在与数据源的交互过程中遇到错误时，它抛出一个`SQLException`实例，而不是`Exception`。

SQLException实例包含以下方法，可以帮助您确定错误的原因：
- `public String getMessage()`：异常描述
- public String getSQLState()：获取SQLState码，多数的代码由 ISO/ANSI 和 Open Group(X/Open) 标准化，但是仍然存在部分的代码由数据库提供商自行实现。
- public int getErrorCode()：与 SQLState 不同，错误代码是由数据库提供商自行定义的整数值，存在是由基础数据源返回的实际错误代码的可能。
- public synchronized Throwable getCause()：表示引发异常的原因，通过不断调用 getCause 方法可以获取异常发生的底层原因。它由一个或多个 Throwable
  对象组成,要浏览这个原因链，递归调用 SQLException.getCause 方法，直到返回一个空值。
- public SQLException getNextException()：如果出现多个错误，则通过此链引用异常。

### SQLWarning

`SQLWarning` 是 `SQLException` 的一个非常重要的子类，用于表示数据库访问时出现的警告。
作为异常，`SQLWarning` 不会停止执行应用程序，而是提醒用户没有按计划发生任何事情。
比如，警告有可能会通知你尝试撤销的某个权限没有成功，或者通知你在请求断开的时间可能发生错误。

`SQLWarning` 有可能被 `Connection` 、`Statement` (包括 `PreparedStatement` 和 `CallableStatement`)
或 `ResultSet` 报告，这些类都存在 `getWarnings` 方法，通过调用该方法才能看到调用对象上报告的第一个警告。
如果 `getWarning` 返回一个警告，我们可以调用其 `getNextWarning` 方法获取下一个警告。
每执行一行语句，那么前面那行语句的警告将会清除，这意味着如果我们想要检索报告处理来的警告，
那么必须在下一行语句执行之前检索。

`DataTruncation` 是最常见的警告，其`SQLState`代码均为`01004`，表示在读取和写入数据的时候存在问题。
`DataTruncation` 有很多方法可以帮助我们去理解哪个列或参数数据被截断，截断是在读取还是写入操作，
应该传输多少字节以及实际传输的字节数。

