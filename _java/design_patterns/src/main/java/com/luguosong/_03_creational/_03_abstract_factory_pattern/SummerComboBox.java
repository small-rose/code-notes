package com.luguosong._03_creational._03_abstract_factory_pattern;

/**
 * Summer组合框类，充当具体产品
 *
 * @author 10545
 * @date 2022/2/22 23:14
 */
public class SummerComboBox implements ComboBox{
    @Override
    public void display() {
        System.out.println("显示蓝色边框组合框");
    }
}
