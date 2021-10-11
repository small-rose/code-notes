package cn.com.lgs;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.SqlSessionManager;
import org.apache.ibatis.session.defaults.DefaultSqlSession;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;


/**
 * @author luguosong
 * @date 2021/10/9 17:37
 */
public class MybatisTest {

    /**
     * 使用xml构建SqlSessionFactory
     */
    @Test
    public void createSqlSessionFactoryByXML() {
        try {
            //读取xml
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            //通过SqlSessionFactoryBuilder创建SqlSessionFactory
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            System.out.println(sqlSessionFactory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createSqlSessionFactoryByCode() {
        //数据库链接池信息
        PooledDataSource pooledDataSource = new PooledDataSource();
        pooledDataSource.setDriver("com.mysql.jdbc.Driver");
        pooledDataSource.setUrl("jdbc:mysql://localhost:3306/code_notes");
        pooledDataSource.setUsername("root");
        pooledDataSource.setPassword("12345678");
        pooledDataSource.setDefaultAutoCommit(false);
        //采用MyBatis的JDBC事务方式
        JdbcTransactionFactory jdbcTransactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", jdbcTransactionFactory, pooledDataSource);
        //创建Configuration对象
        Configuration configuration = new Configuration(environment);
        //构建SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        System.out.println(sqlSessionFactory);
    }
}
