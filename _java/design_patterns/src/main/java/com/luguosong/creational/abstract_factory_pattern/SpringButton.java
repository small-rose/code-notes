package com.luguosong.creational.abstract_factory_pattern;

/**
 * Spring按钮类，充当具体产品
 *
 * @author 10545
 * @date 2022/2/22 22:29
 */
public class SpringButton implements Button{
    @Override
    public void display() {
        System.out.println("显示浅绿色按钮");
    }
}
