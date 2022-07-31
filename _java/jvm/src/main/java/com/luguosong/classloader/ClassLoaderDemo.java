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
        //sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println("系统类加载器:" + systemClassLoader);

        //获取扩展类加载器
        ClassLoader extClassLoader = systemClassLoader.getParent();
        //sun.misc.Launcher$ExtClassLoader@1b6d3586
        System.out.println("扩展类加载器:" + extClassLoader);

        //试图获取BootstrapClassLoader
        ClassLoader bootstrapClassLoader = extClassLoader.getParent();
        //null
        System.out.println(bootstrapClassLoader);


        //用户自己编写的类的ClassLoader
        ClassLoader classLoader = ClassLoaderDemo.class.getClassLoader();
        //sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(classLoader);

        //Java的核心类库都是通过引导类加载器加载的
        //String类获取类加载器
        ClassLoader classLoader1 = String.class.getClassLoader();
        //null
        System.out.println(classLoader1);


    }
}
