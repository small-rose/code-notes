package com.luguosong;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 原型bean测试
 *
 * @author luguosong
 * @date 2022/5/29 10:10
 */
public class PrototypeDemo {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user1 = (User) context.getBean("user2");
        User user2 = (User) context.getBean("user2");
        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user1==user2);
    }
}
