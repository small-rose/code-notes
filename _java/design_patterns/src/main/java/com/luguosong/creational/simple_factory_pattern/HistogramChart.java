package com.luguosong.creational.simple_factory_pattern;

/**
 * 柱状图类
 *
 * @author luguosong
 * @date 2022/2/2 9:58
 */
public class HistogramChart implements Chart{

    public HistogramChart(){
        System.out.println("创建柱状图！");
    }

    @Override
    public void display() {
        System.out.println("显示柱状图！");
    }
}
