package cn.com.lgs.abstract_factory_pattern;

/**
 * @author 10545
 * @date 2022/2/22 22:48
 */
public class SummerTextField implements TextField{
    @Override
    public void display() {
        System.out.println("显示蓝色边框文本框");
    }
}
