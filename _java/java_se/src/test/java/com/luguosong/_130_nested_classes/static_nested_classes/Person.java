package com.luguosong._130_nested_classes.static_nested_classes;

/**
 * 静态嵌套类
 *
 * @author luguosong
 * @date 2022/11/23
 */
public class Person {
    private static String name = "李四";

    private int age = 18;

    public static class Car {

        public void test() {
            System.out.println("静态类直接获取外部类的私有静态成员：" + name);

            Person person = new Person();
            System.out.println("通过外部类的对象获取外部类私有成员："+person.age);
        }
    }
}
