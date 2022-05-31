package com.luguosong;

/**
 * 被代理的类，采用cglib，没有接口
 *
 * @author luguosong
 * @date 2022/5/31 17:30
 */
public class User {
    public void hello(){
        System.out.println("hello");
    }
}
