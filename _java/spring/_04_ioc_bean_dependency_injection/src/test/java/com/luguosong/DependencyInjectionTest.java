package com.luguosong;

import com.luguosong.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author luguosong
 * @date 2022/5/29 11:27
 */
public class DependencyInjectionTest {
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    /**
     * Spring使用bean的property子标签通过setter方法进行依赖注入
     */
    @Test
    public void dependencyInjectionBySetter(){
        UserService userService1 = (UserService)context.getBean("userService1");
        userService1.hello();
    }

    /**
     * Spring使用p命名空间通过setter方法进行依赖注入
     */
    @Test
    public void dependencyInjectionBySetterP(){
        UserService userService1 = (UserService)context.getBean("userService2");
        userService1.hello();
    }

    /**
     * Spring使用bean的constructor-arg子标签通过有参构造进行依赖注入
     */
    @Test
    public void dependencyInjectionByConstructor(){
        UserService userService1 = (UserService)context.getBean("userService3");
        userService1.hello();
    }
}
