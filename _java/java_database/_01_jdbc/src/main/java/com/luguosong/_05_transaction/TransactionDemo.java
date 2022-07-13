package com.luguosong._05_transaction;

import java.sql.*;

/**
 * 事务
 *
 * @author 10545
 * @date 2022/7/11 21:32
 */
public class TransactionDemo {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/java_database",
                    "root",
                    "12345678");
            //开启事务
            connection.setAutoCommit(false);

            preparedStatement1 = connection.prepareStatement("UPDATE account SET balance = balance - ? WHERE id = ?");
            preparedStatement1.setInt(1, 100);
            preparedStatement1.setInt(2, 1);
            preparedStatement1.executeUpdate();

            //模拟异常
            int i = 1 / 0;

            preparedStatement2 = connection.prepareStatement("UPDATE account SET balance = balance + ? WHERE id = ?");
            preparedStatement2.setInt(1, 100);
            preparedStatement2.setInt(2, 2);
            preparedStatement2.executeUpdate();

            //提交事务
            connection.commit();
        } catch (Exception e) {
            try {
                if (connection != null) {
                    //回滚事务
                    connection.rollback();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (preparedStatement1 != null) {
                try {
                    preparedStatement1.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (preparedStatement2 != null) {
                try {
                    preparedStatement2.close();
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
