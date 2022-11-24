package com.luguosong._20_variables;

/**
 * 字，字面值
 *
 * @author luguosong
 * @date 2022/9/8
 */
public class LiteralsDemo {

    /**
     * 整型字，默认就是int类型
     */
    int i = 2147483647;

    /**
     * 数字之间可以采用下划线分隔
     */
    int i2 = 2_147_483_647;

    /**
     * 整形字结尾加上L，表示long类型
     */
    long l1 = 9223372036854775807L;


    /**
     * int类型的字，可以自动向上转型为long数据类型
     */
    long l2 = 2147483647;


    /**
     * 127是int类型的字，int类型的字可以创建byte类型变量
     * 但要注意，字的范围必须在byte类型范围内，否则得强制类型转换
     */
    byte b1 = 'b';
    byte b2 = 127;
    byte b3 = (byte) 128; //128已经超出了byte的取值范围，需要进行强制类型转换

    /**
     * 32767是int类型的字,int类型的字可以创建short类型变量
     * 但要注意，字的范围必须在short类型范围内，否则得强制类型转换
     */
    short s1 = 32767;
    short s2 = (short) 32768;


    /**
     * 数字 26，十进制
     */
    int decVal = 26;
    /**
     * 数字 26，十六进制
     */
    int hexVal = 0x1a;
    /**
     * 数字 26，二进制
     */
    int binVal = 0b11010;

    /**
     * 浮点字默认表示double类型
     */
    double d1 = 19.1;

    /**
     * 如果要声明float类型，可以在浮点字后面加f
     * 或者进行强制类型转换
     */
    float f = 19.1f;
    float f2 = (float) 19.1;

    /**
     * 科学计数法浮点字
     */
    double d2 = 1.234e2; //123.4


    /**
     * 字符字
     */
    char c1 = 'c'; //字符
    char c2 = '\u0063'; //转义字符
    char c3 = 99; //int

    /**
     * 字符串字
     */
    String string1 = "ccc";


}
