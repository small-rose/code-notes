package com.luguosong._04_structural._04_decorator_pattern;

/**
 * 黑色边框装饰类，充当具体装饰类
 *
 * @author luguosong
 * @date 2022/5/15 16:38
 */
public class BlackBorderDecorator extends ComponentDecorator{
    public BlackBorderDecorator(Component component) {
        super(component);
    }

    /**
     * 重写抽象装饰类方法
     */
    @Override
    public void display() {
        setBlackBorder();
        super.display();
    }

    /**
     * 装饰方法
     */
    public void setBlackBorder(){
        System.out.println("为构件增加黑色边框");
    }
}
