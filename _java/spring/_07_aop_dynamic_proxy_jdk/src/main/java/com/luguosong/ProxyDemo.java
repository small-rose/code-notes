package com.luguosong;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author 10545
 * @date 2022/5/30 23:10
 */
public class ProxyDemo {

    public static void main(String[] args) {

        UserImpl user=new UserImpl();

        User userProxy= (User)Proxy.newProxyInstance(
                user.getClass().getClassLoader(),
                user.getClass().getInterfaces(),
                (Object proxy, Method method,Object[] args1)->{
                    //只对hello方法进行增强，hello2方法不进行增强
                    if ("hello".equals(method.getName())){
                        System.out.println("前置增强");
                    }
                    Object invoke = method.invoke(user, args1);
                    if ("hello".equals(method.getName())){
                        System.out.println("后置增强");
                    }
                    return invoke;
                }
        );
        userProxy.hello();
        userProxy.hello2();
    }
}

