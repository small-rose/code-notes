package com.luguosong._180_arrays;

/**
 * 多维数组
 *
 * @author luguosong
 * @date 2022/9/14
 */
public class MultiDimArrayDemo {
    public static void main(String[] args) {
        String[][] names = {
                {"Mr. ", "Mrs. ", "Ms. "},
                {"Smith", "Jones"}
        };
        // Mr. Smith
        System.out.println(names[0][0] + names[1][0]);
        // Ms. Jones
        System.out.println(names[0][2] + names[1][1]);
        //2
        System.out.println(names.length);
    }
}
