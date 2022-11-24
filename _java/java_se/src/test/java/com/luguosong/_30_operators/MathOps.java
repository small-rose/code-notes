package com.luguosong._30_operators;

import java.util.Random;

/**
 * 算数运算符
 *
 * @author luguosong
 * @date 2022/9/15
 */
public class MathOps {
    public static void main(String[] args) {
        // 创建一个种子随机数生成器
        Random rand = new Random(47);
        int i, j, k;
        // 从1~100的范围中选择：
        j = rand.nextInt(100) + 1;
        System.out.println("j : " + j);
        k = rand.nextInt(100) + 1;
        System.out.println("k : " + k);
        i = j + k;
        System.out.println("j + k : " + i);
        i = j - k;
        System.out.println("j - k : " + i);
        i = k / j;
        System.out.println("k / j : " + i);
        i = k * j;
        System.out.println("k * j : " + i);
        i = k % j;
        System.out.println("k % j : " + i);
        j %= k;
        System.out.println("j %= k : " + j);
        // 单精度浮点数（float）测试：
        float u, v, w; // 同样适用于双精度浮点数（double）
        v = rand.nextFloat();
        System.out.println("v : " + v);
        w = rand.nextFloat();
        System.out.println("w : " + w);
        u = v + w;
        System.out.println("v + w : " + u);
        u = v - w;
        System.out.println("v - w : " + u);
        u = v * w;
        System.out.println("v * w : " + u);
        u = v / w;
        System.out.println("v / w : " + u);
        // 以下对char、byte、short、int、long和double都适用
        u += v;
        System.out.println("u += v : " + u);
        u -= v;
        System.out.println("u -= v : " + u);
        u *= v;
        System.out.println("u *= v : " + u);
        u /= v;
        System.out.println("u /= v : " + u);
    }
}
