package cn.com.lgs.adapter_pattern;

/**
 * 客户端测试类
 *
 * @author 10545
 * @date 2022/5/2 21:12
 */
public class Demo {
    public static void main(String[] args) {
        CarController car;
        car = (CarController) XMLUtil.getBean();
        car.move();
        car.phonate();
        car.twinkle();
    }
}
