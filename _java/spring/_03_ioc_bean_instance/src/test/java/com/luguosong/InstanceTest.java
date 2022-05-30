package com.luguosong;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author luguosong
 * @date 2022/5/29 10:28
 */
public class InstanceTest {

    ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");

    @Test
    public void createInstanceByConstructor(){
        User user1 = (User) context.getBean("user1");
        user1.hello();
    }

    @Test
    public void createInstanceByStaticFactory(){
        User user1 = (User) context.getBean("user2");
        user1.hello();
    }

    @Test
    public void createInstanceByFactory(){
        User user1 = (User) context.getBean("user3");
        user1.hello();
    }
}
