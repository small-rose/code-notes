package com.luguosong.creational.abstract_factory_pattern;

/**
 * Spring组合框类，充当具体产品
 *
 * @author 10545
 * @date 2022/2/22 22:55
 */
public class SpringComboBox implements ComboBox{
    @Override
    public void display() {
        System.out.println("显示绿色边框组合框");
    }
}
