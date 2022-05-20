package com.luguosong._03_creational._01_simple_factory_pattern;

/**
 * 折线图类
 *
 * @author luguosong
 * @date 2022/2/2 10:02
 */
public class LineChart implements Chart{

    public LineChart(){
        System.out.println("创建折线图！");
    }

    @Override
    public void display() {
        System.out.println("显示折线图！");
    }
}
