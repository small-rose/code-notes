package com.luguosong._30_operators;

/**
 *  ++和--操作符
 *
 * @author luguosong
 * @date 2022/9/15
 */
public class AutoInc {
    public static void main(String[] args) {
        int i1=1;
        System.out.println(i1);
        System.out.println(i1++); //先返回变量的值，然后再执行运算
        System.out.println(i1);

        int i2=1;
        System.out.println(i2);
        System.out.println(++i2); //先执行运算，然后返回生成的结果
        System.out.println(i2);
    }
}
