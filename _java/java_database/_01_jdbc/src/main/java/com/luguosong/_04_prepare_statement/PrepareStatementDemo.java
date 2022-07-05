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
