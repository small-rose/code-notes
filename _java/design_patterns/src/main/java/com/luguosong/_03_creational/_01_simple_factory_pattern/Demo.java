package com.luguosong._03_creational._01_simple_factory_pattern;

/**
 * @author luguosong
 * @date 2022/2/2 10:10
 */
public class Demo {
    public static void main(String[] args) {
        Chart chart;
        chart = ChartFactory.getChart("histogram"); //通过静态工厂方法创建产品
        chart.display();
    }
}
