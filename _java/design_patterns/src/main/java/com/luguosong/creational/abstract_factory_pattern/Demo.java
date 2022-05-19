package com.luguosong.creational.abstract_factory_pattern;

/**
 * 客户端调用
 *
 * @author 10545
 * @date 2022/2/28 22:22
 */
public class Demo {
    public static void main(String[] args) {
        //使用抽象层定义
        SkinFactory factory;
        Button bt;
        TextField tf;
        ComboBox cb;
        //使用工具类创建工厂
        factory=(SkinFactory)XMLUtil.getBean();
        //工厂创建对象
        bt=factory.createButton();
        tf=factory.createTextField();
        cb=factory.createComboBox();

        //运行具体产品
        bt.display();
        tf.display();
        cb.display();
    }
}
