package com.luguosong._03_creational._01_simple_factory_pattern;

/**
 * 饼状图类
 *
 * @author luguosong
 * @date 2022/2/2 10:00
 */
public class PieChart implements Chart{

    public PieChart(){
        System.out.println("创建饼状图！");
    }

    @Override
    public void display() {
        System.out.println("显示饼状图！");
    }
}
