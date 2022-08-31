package com.luguosong;

import com.luguosong.util.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author luguosong
 * @date 2022/7/2
 */
public class Demo {
    public static void main(String[] args) {
        try {
            Connection connection = JDBCUtils.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user");
            while (resultSet.next()) {
                String column1 = resultSet.getString(1);
                System.out.print(column1 + " ");

                String column2 = resultSet.getString("name");
                System.out.print(column2 + " ");

                String column3 = resultSet.getString(3);
                System.out.println(column3 + " ");
            }
            JDBCUtils.close(resultSet, statement, connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
