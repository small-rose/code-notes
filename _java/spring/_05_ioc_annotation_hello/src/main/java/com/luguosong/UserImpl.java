package com.luguosong;

import org.springframework.stereotype.Component;

/**
 * @author luguosong
 * @date 2022/5/29 20:56
 */
@Component
public class UserImpl implements User{
    @Override
    public void hello() {
        System.out.println("hello");
    }
}
