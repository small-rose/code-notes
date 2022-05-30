package com.luguosong;

import com.luguosong.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author luguosong
 * @date 2022/5/30 15:12
 */
public class AnnotationTest1 {
    private ApplicationContext app=new AnnotationConfigApplicationContext(SpringConfiguration.class);

    @Test
    public void test(){
        UserService userService = app.getBean(UserService.class);
        userService.hello();
    }
}
