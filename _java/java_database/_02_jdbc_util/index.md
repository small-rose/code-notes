---
layout: default
title: jdbc工具类
nav_order: 20
parent: Java数据库相关
---

# JDBC工具类

把一些重复代码封装成工具类,如`获取连接`和`关闭连接`

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
