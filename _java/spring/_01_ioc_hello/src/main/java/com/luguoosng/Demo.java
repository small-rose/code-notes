package com.luguoosng;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author luguosong
 * @date 2022/5/27 10:12
 */
public class Demo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user = (User)app.getBean("user");
        user.hello();
    }
}
