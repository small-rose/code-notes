package com.luguosong;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author luguosong
 * @date 2022/6/1 11:23
 */
@Configuration
@ComponentScan("com.luguosong")
@EnableAspectJAutoProxy //AOP自动代理
public class SpringConfig {
}
