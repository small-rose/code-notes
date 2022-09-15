package com.luguosong._30_operators;

/**
 * 对象是否相等，Integer为例
 *
 * @author luguosong
 * @date 2022/9/15
 */
public class Equivalence {
    static void show(String desc, Integer n1, Integer n2) {
        System.out.println(desc + ":");
        System.out.printf(
                "%d==%d %b %b%n", n1, n2, n1 == n2, n1.equals(n2));
    }

    /**
     * [1] 自动转换为Integer。这其实是通过对Integer.valueOf()的自动调用来完成的。
     * [2] 使用标准的对象创建语法new。这是以前创建“包装/装箱”Integer对象的首选方法。
     * [3] 从Java 9开始，valueOf()优于[2]。如果尝试在Java 9中使用方式[2]，你将收到警告，并被建议使用[3]代替。很难确定[3]是否的确优于[1]，不过[1]看起来更简洁。
     * [4] 基本类型int也可以当作整数值对象使用。
     *
     * @param value
     */
    //@SuppressWarnings("deprecation")
    public static void test(int value) {
        Integer i1 = value;                             // [1]
        Integer i2 = value;
        show("Automatic", i1, i2);
        // 在Java 9及更新版本中已被弃用的旧方式：
        Integer r1 = new Integer(value);                // [2]
        Integer r2 = new Integer(value);
        show("new Integer()", r1, r2);
        // Java 9及更新版本中提倡的方式：
        Integer v1 = Integer.valueOf(value);            // [3]
        Integer v2 = Integer.valueOf(value);
        show("Integer.valueOf()", v1, v2);
        // 基本类型不能使用equals()方法:
        int x = value;                                  // [4]
        int y = value;
        // x.equals(y); // 无法编译
        System.out.println("Primitive int:");
        System.out.printf("%d==%d %b%n", x, y, x == y);
    }
    public static void main(String[] args) {
        test(127);
        System.out.println("----------");

        //出于效率原因，Integer会通过享元模式来缓存范围在-128~127内的对象
        // 因此多次调用Integer.valueOf(127)生成的其实是同一个对象。
        // 而在此范围之外的值则不会这样
        test(128);
    }
}
