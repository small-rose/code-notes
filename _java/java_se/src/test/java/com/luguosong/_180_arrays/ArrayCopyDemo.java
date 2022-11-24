package com.luguosong._180_arrays;

/**
 * 数组复制
 *
 * @author luguosong
 * @date 2022/9/14
 */
public class ArrayCopyDemo {
    public static void main(String[] args) {
        char[] copyFrom = {
                'd', 'e', 'c', 'a', 'f', 'f', 'e', 'i', 'n', 'a', 't', 'e', 'd' };
        char[] copyTo = new char[7];
        //调用arraycopy复制数组
        System.arraycopy(copyFrom, 2, copyTo, 0, 7);
        System.out.println(new String(copyTo));
    }
}
