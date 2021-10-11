package cn.com.lgs;

import cn.com.lgs.mapper.PeopleMapper;
import cn.com.lgs.mapper.PeopleMapperByAnnotation;
import cn.com.lgs.pojo.People;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
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

    /**
     * 通过代码创建SqlSessionFactory
     */
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

    /**
     * 创建SqlSession对象，并设置事务
     */
    @Test
    public void createSqlSession() {
        SqlSession sqlSession = null;
        try {
            //读取xml
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            //通过SqlSessionFactoryBuilder创建SqlSessionFactory
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            //创建SqlSession对象
            sqlSession = sqlSessionFactory.openSession();
            //some code ...
            //提交事务
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            //回滚事务
            sqlSession.rollback();
        } finally {
            if (sqlSession != null) {
                //关闭资源
                sqlSession.close();
            }
        }
    }

    /**
     * 使用代码方式配置映射器
     */
    public void configMapper() {
        //数据库链接池信息
        PooledDataSource pooledDataSource = new PooledDataSource();
        pooledDataSource.setDriver("com.mysql.jdbc.Driver");
        pooledDataSource.setUrl("jdbc:mysql://localhost:3306/code_notes");
        pooledDataSource.setUsername("root");
        pooledDataSource.setPassword("12345678");
        pooledDataSource.setDefaultAutoCommit(false);
        JdbcTransactionFactory jdbcTransactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", jdbcTransactionFactory, pooledDataSource);
        //创建Configuration对象
        Configuration configuration = new Configuration(environment);


        //配置注解映射器
        configuration.addMapper(PeopleMapperByAnnotation.class);


        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        // 获取SqlSession对象执行sql
        // ...
    }

    /**
     * 使用SqlSession发送SQL
     */
    @Test
    public void sendSqlBySqlSession() {
        SqlSession sqlSession = null;
        try {
            //读取xml
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            //通过SqlSessionFactoryBuilder创建SqlSessionFactory
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            //创建SqlSession对象
            sqlSession = sqlSessionFactory.openSession();


            //使用SqlSession发送SQL
            //xml映射器
            People people1 = (People) sqlSession.selectOne("getPeople", 1);
            System.out.println(people1);
            //注解映射器
            Object people2 = sqlSession.selectOne("getPeopleByAnnotation", 2);
            System.out.println(people2);


            //提交事务
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            //回滚事务
            sqlSession.rollback();
        } finally {
            if (sqlSession != null) {
                //关闭资源
                sqlSession.close();
            }
        }
    }

    /**
     * 通过Mapper发送SQL
     */
    @Test
    public void sendSqlByMapper() {
        SqlSession sqlSession = null;
        try {
            //读取xml
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            //通过SqlSessionFactoryBuilder创建SqlSessionFactory
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            //创建SqlSession对象
            sqlSession = sqlSessionFactory.openSession();


            //使用Mapper发送SQL
            PeopleMapper mapper1 = sqlSession.getMapper(PeopleMapper.class);
            People people1 = mapper1.getPeople(1);
            System.out.println(people1);

            PeopleMapperByAnnotation mapper2 = sqlSession.getMapper(PeopleMapperByAnnotation.class);
            People people2 = mapper2.getPeopleByAnnotation(2);
            System.out.println(people2);

            //提交事务
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            //回滚事务
            sqlSession.rollback();
        } finally {
            if (sqlSession != null) {
                //关闭资源
                sqlSession.close();
            }
        }
    }
}
