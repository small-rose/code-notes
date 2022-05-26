package com.luguosong._04_structural._01_adapter_pattern;

/**
 * 汽车控制类，充当目标抽象类
 *
 * @author 10545
 * @date 2022/5/2 17:07
 */
public abstract class CarController {
    public void move() {
        System.out.println("玩具汽车移动！");
    }

    public abstract void phonate();

    public abstract void twinkle();
}
