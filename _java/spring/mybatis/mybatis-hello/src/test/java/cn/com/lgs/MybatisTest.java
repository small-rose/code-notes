package cn.com.lgs;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
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
        PooledDataSource pooledDataSource = new PooledDataSource();
        pooledDataSource.setDriver("com.mysql.jdbc.Driver");
        pooledDataSource.setUrl("jdbc:mysql://localhost:3306/code_notes");
        pooledDataSource.setUsername("");
    }
}
