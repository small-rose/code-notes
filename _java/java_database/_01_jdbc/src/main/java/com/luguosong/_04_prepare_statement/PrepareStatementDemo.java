package com.luguosong._04_prepare_statement;

import java.sql.*;

/**
 * @author luguosong
 * @date 2022/7/3
 */
public class PrepareStatementDemo {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/java_database",
                    "root",
                    "12345678");

            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user WHERE name='张三' AND password='a' OR 'a' = 'a'");
            System.out.println("静态sql查询结果："+resultSet.next());

            connection.prepareStatement("SELECT * FROM user WHERE name=? AND password=?");
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
