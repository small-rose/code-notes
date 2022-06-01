package com.luguosong;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author luguosong
 * @date 2022/6/1 9:25
 */
@Component
@Aspect //标注当前类是切面类
public class UserAspect {

    @Before("execution(void com.luguosong.User.hello())")
    public void before(){
        System.out.println("前置增强");
    }
}
