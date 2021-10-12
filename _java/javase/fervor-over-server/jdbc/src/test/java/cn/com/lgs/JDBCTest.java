package cn.com.lgs;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.junit.jupiter.api.Test;

import java.beans.PropertyVetoException;
import java.sql.*;

/**
 * @author luguosong
 * @date 2021/10/12 13:59
 */
public class JDBCTest {

    /**
     * jdbc入门
     */
    @Test
    public void jdbcHelloWorld() {
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

    /**
     * 测试MySQL驱动内部实现的DataSource接口
     */
    @Test
    public void testMysqlDataSource() {
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

    /**
     * 测试c3p0
     */
    @Test
    public void testC3p0() {
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
        }  catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试druid
     */
    @Test
    public void testDruid() {
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
