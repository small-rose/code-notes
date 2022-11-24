package com.luguosong._50_control_flow_statements;

/**
 * @author luguosong
 * @date 2022/9/21
 */
public class LabeledFor {
    public static void main(String[] args) {
        int i = 0;
        outer:
        // 此处不能有语句
        for (; true; ) { // 无限循环
            inner:
            // 此处不能有语句
            for (; i < 10; i++) {
                System.out.println("i = " + i);
                if (i == 2) {
                    System.out.println("continue");
                    continue;
                }
                if (i == 3) {
                    System.out.println("break");
                    i++; // 否则i不会递增
                    break;
                }
                if (i == 7) {
                    System.out.println("continue outer");
                    i++; // 否则i不会递增
                    continue outer;
                }
                if (i == 8) {
                    System.out.println("break outer");
                    break outer;
                }
                for (int k = 0; k < 5; k++) {
                    if (k == 3) {
                        System.out.println("continue inner");
                        continue inner;
                    }
                }
            }
        }
        // 此处不能有标签
    }
}
