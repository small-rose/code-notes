package com.luguosong._03_creational.abstract_factory_pattern;

/**
 * Spring文本框类，充当具体产品
 *
 * @author 10545
 * @date 2022/2/22 22:46
 */
public class SpringTextField implements TextField{
    @Override
    public void display() {
        System.out.println("显示绿色边框文本框！");
    }
}
