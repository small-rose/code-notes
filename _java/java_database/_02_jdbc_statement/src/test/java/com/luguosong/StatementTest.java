package com.luguosong;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author 10545
 * @date 2022/6/2 0:02
 */
public class StatementTest {
    private static Connection conn;
    private static Statement statement;

    @BeforeAll
    public static void init() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/database_learning", "root", "12345678");
            statement = conn.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void addTest() {
        try {
            int count = statement.executeUpdate("INSERT INTO products VALUES ('AA',1004,'coffee',2.50,'describe')");
            System.out.println("添加结果：" + count);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterAll
    public static void close() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
