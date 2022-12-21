package com.luguosong.design_patterns._04_behavioral._09_strategy_pattern;

import java.util.Scanner;

/**
 * 策略模式反面案例
 *
 * 缺点一：每次增加新算法后，主类体积就会增加，
 * 到了某个时间点将无法维护这堆代码
 *
 * 缺点二：对于简单需求的修改，将影响整个类。
 * 增加在已有正常运行代码中引入错误的风险。
 *
 * 缺点三：团队合作变得低效，的团队需要修改同一个巨大的类。
 * 这样团队成员编写代码会出现冲突
 *
 * @author luguosong
 * @date 2022/12/20
 */
public class NegativeDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = "";
        do {
            System.out.print("请输入策略类型：");
            s = scanner.nextLine();
            switch (s) {
                case "A":
                    System.out.println("算法A");
                    break;
                case "B":
                    System.out.println("算法B");
                    break;
                case "C":
                    System.out.println("算法C");
                    break;
                default:
                    System.out.println("算法类型错误");
            }
        }
        while (!"exit".equals(s));
    }
}
