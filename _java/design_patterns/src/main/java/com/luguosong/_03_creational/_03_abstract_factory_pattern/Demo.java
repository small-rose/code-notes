package com.luguosong._03_creational._03_abstract_factory_pattern;

import com.luguosong.util.XMLUtil;

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
        factory=(SkinFactory) XMLUtil.getBean("_java/design_patterns/src/main/java/com/luguosong/_03_creational/_03_abstract_factory_pattern/config.xml").get(0);
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
