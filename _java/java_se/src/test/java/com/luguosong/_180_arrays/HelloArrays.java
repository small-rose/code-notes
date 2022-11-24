package com.luguosong._180_arrays;

/**
 * @author luguosong
 * @date 2022/9/14
 */
public class HelloArrays {

    public static void main(String[] args) {
        //方式一
        //创建数组
        int[] anArray1 = new int[10];
        //初始化数组
        anArray1[0] = 100;
        //访问数组
        System.out.println(anArray1[0]);

        //方式二
        //创建并初始化数组
        int[] anArray2 = {100, 200, 300};
        //访问数组
        System.out.println(anArray2[0]);
    }
}
