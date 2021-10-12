package cn.com.lgs;

import cn.com.lgs.mapper.PeopleMapper;
import cn.com.lgs.pojo.People;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author luguosong
 * @date 2021/10/11 18:19
 */
public class MyBatisTest {

    /**
     * 使用properties子标签property配置属性
     */
    @Test
    public void propertiesTest1(){
        SqlSession sqlSession=null;
        try {
            InputStream resource = Resources.getResourceAsStream("mybatis-properties1-config.xml");
            SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resource);
            sqlSession = build.openSession();
            PeopleMapper mapper = sqlSession.getMapper(PeopleMapper.class);
            People people = mapper.getPeople(1);
            System.out.println(people);
        } catch (IOException e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 使用properties配置文件配置参数
     */
    @Test
    public void propertiesTest2(){
        SqlSession sqlSession=null;
        try {
            InputStream resource = Resources.getResourceAsStream("mybatis-properties2-config.xml");
            SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resource);
            sqlSession = build.openSession();
            PeopleMapper mapper = sqlSession.getMapper(PeopleMapper.class);
            People people = mapper.getPeople(1);
            System.out.println(people);
        } catch (IOException e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }
}
