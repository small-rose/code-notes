package com.luguosong;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author luguosong
 * @date 2022/5/31 17:45
 */
public class Demo {
    public static void main(String[] args) {
        User user = new User();

        //创建增强器
        Enhancer enhancer = new Enhancer();
        //设置父类（也就是要被代理的类）
        enhancer.setSuperclass(user.getClass());
        //设置回调
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("前置增强");
                Object invoke = method.invoke(user, objects);
                System.out.println("后置增强");
                return invoke;
            }
        });
        //生成代理对象，动态生成的代理类是被代理类的子类
        User proxy = (User)enhancer.create();
        //执行被增强后的方法
        proxy.hello();
    }
}
