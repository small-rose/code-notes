package cn.com.lgs.introduction;

import sun.security.util.ArrayUtil;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 算法有穷性举例
 *
 * 这个例子是否有穷不确定
 *
 * 输入n
 * 如果n<=1,结束
 * 如果n为偶数，n/2
 * 如果n为基数，3n+1
 *
 * @author luguosong
 * @date 2021/12/6 18:21
 */
public class Hailstone {
    public static void main(String[] args) {
        System.out.print("请输入一个数：");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int length = 1;
        while (n > 1) {
            n = (n % 2 == 1) ? 3 * n + 1 : n / 2;
            System.out.println(n);
            length++;
        }
        System.out.println("结果：" + length);
    }
}
