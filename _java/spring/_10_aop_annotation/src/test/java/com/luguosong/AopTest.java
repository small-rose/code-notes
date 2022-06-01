package com.luguosong;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * @author luguosong
 * @date 2022/6/1 11:25
 */
@SpringJUnitConfig(SpringConfig.class)
public class AopTest {
    @Autowired
    private User user;

    @Test
    public void test(){
        user.hello();
    }
}
