package com.luguosong._01_hello_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
                    "jdbc:mysql://localhost:3306/java_database",
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
