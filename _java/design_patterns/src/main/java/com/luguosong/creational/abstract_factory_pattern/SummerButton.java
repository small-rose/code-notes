package com.luguosong.creational.abstract_factory_pattern;

/**
 * Summer按钮类，充当具体产品
 *
 * @author 10545
 * @date 2022/2/22 22:32
 */
public class SummerButton implements Button{
    @Override
    public void display() {
        System.out.println("显示浅蓝色按钮");
    }
}
