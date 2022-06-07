package com.luguosong._04_structural._01_adapter_pattern;

import com.luguosong.util.XMLUtil;

/**
 * 客户端测试类
 *
 * @author 10545
 * @date 2022/5/2 21:12
 */
public class Demo {
    public static void main(String[] args) {
        CarController car;
        car = (CarController) XMLUtil.getBean("_java/design_patterns/src/main/java/com/luguosong/_04_structural/_01_adapter_pattern/config.xml").get(0);
        car.move();
        car.phonate();
        car.twinkle();
    }
}
