package com.luguosong._03_result_set;

import java.sql.*;

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
                    "jdbc:mysql://localhost:3306/java_database",
                    "root",
                    "12345678");
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user");
            while (resultSet.next()) {
                String column1 = resultSet.getString(1);
                System.out.print(column1 + " ");

                String column2 = resultSet.getString("name");
                System.out.print(column2 + " ");

                String column3 = resultSet.getString(3);
                System.out.println(column3 + " ");
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
