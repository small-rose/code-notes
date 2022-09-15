package com.luguosong._180_arrays;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 数组操作工具类
 *
 * @author luguosong
 * @date 2022/9/14
 */
public class ArraysUtilDemo {
    public static void main(String[] args) {
        char[] copyFrom = {
                'd', 'e', 'c', 'a', 'f', 'f', 'e', 'i', 'n', 'a', 't', 'e', 'd'};
        char[] copyTo = new char[7];

        //数组复制
        copyTo = Arrays.copyOfRange(copyFrom, 2, 9);
        System.out.println(new String(copyTo));

        //在数组中搜索特定值，并返回位置索引
        int index = Arrays.binarySearch(copyFrom, 'e');
        System.out.println(index);

        //比较两个数组是否相等
        boolean equals = Arrays.equals(copyFrom, copyTo);
        System.out.println(equals);

        //将指定位置上的元素替换成指定值
        Arrays.fill(copyTo, 0, 2, 'z');
        System.out.println(new String(copyTo));

        //将全部元素替换成指定值
        Arrays.fill(copyTo,'x');
        System.out.println(new String(copyTo));

        //排序
        Arrays.sort(copyFrom);
        //多处理器系统中大数组的并发排序比顺序排序要快
        Arrays.parallelSort(copyFrom);
        System.out.println(new String(copyFrom));
    }
}
