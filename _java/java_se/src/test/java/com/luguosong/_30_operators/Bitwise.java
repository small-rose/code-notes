package com.luguosong._30_operators;

/**
 * 按位操作符
 * @author luguosong
 * @date 2022/9/15
 */
public class Bitwise {
    public static void main(String[] args) {
        //11
        System.out.println(Integer.toBinaryString(3));
        //101
        System.out.println(Integer.toBinaryString(5));
        //按位“与”操作符
        //11 & 101 = 1
        System.out.println(Integer.toBinaryString(3&5));
        //按位“或”操作符
        //11 | 101 =111
        System.out.println(Integer.toBinaryString(3|5));
        //按位“异或”操作符,两边不一样返回1
        //11^101 =110
        System.out.println(Integer.toBinaryString(3^5));
        //按位“非 ”操作符
        //~101 = 11111111111111111111111111111010
        System.out.println(Integer.toBinaryString(~5));
    }
}
