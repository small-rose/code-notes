package com.luguosong._03_factory;

import com.luguosong.User;
import com.luguosong.UserImpl;

/**
 * 使用非静态工厂方法创建对象
 *
 * @author luguosong
 * @date 2022/5/29 10:46
 */
public class UserFactory {
    public User getUser() {
        return new UserImpl();
    }
}
