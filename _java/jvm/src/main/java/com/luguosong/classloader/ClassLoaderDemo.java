package com.luguosong.classloader;

import sun.misc.Launcher;

/**
 * @author luguosong
 * @date 2022/7/16
 */
public class ClassLoaderDemo {
    public static void main(String[] args) {
        //获取系统类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);
    }
}
