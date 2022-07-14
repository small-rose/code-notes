package com.luguosong.classloader;

/**
 * @author 10545
 * @date 2022/7/14 22:49
 */
public class HelloApp {
    private static int a = 10;

    static {
        a = 20;
    }

    public static void main(String[] args) {
        System.out.println(a);
    }
}
