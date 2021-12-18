package cn.com.lgs.control_flow;

import com.sun.org.apache.xpath.internal.operations.String;

/**
 * Java中没有goto
 * continue和break都有类似goto的功能
 *
 * @author 10545
 * @date 2021/11/26 20:49
 */
public class GotoDemo {
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
