package cn.com.lgs.abstract_factory_pattern;

/**
 * Summer文本框类，充当具体产品
 *
 * @author 10545
 * @date 2022/2/22 22:48
 */
public class SummerTextField implements TextField{
    @Override
    public void display() {
        System.out.println("显示蓝色边框文本框");
    }
}
