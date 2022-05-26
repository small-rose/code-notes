package com.luguosong._04_structural._04_decorator_pattern;

/**
 * 案例
 *
 * @author luguosong
 * @date 2022/5/15 16:41
 */
public class Demo {
    public static void main(String[] args) {
        Component component,componentSB;
        component=new Windows();
        componentSB=new ScrollBarDecorator(component);
        componentSB.display();
    }
}
