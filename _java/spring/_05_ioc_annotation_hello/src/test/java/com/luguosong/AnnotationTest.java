package com.luguosong;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * @author luguosong
 * @date 2022/5/29 20:59
 */
@SpringJUnitConfig(locations = "classpath:applicationContext.xml")
public class AnnotationTest {
    @Autowired
    private User user;

    @Test
    public void test(){
        user.hello();
    }
}
