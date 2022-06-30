package com.luguosong._02_statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
