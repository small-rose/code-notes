package com.luguosong._02_static_factory;

import com.luguosong.User;
import com.luguosong.UserImpl;

/**
 * 静态工厂方法返回user对象
 *
 * @author luguosong
 * @date 2022/5/29 10:40
 */
public class UserStaticFactory {
    public static User getUser(){
        return new UserImpl();
    }
}
