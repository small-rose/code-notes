package com.luguosong._50_control_flow_statements;

import java.util.Random;

/**
 * for-in
 *
 * @author luguosong
 * @date 2022/9/21
 */
public class ForInFloat {
    public static void main(String[] args) {
        Random rand = new Random(47);
        float[] f = new float[10];
        for (int i = 0; i < 10; i++)
            f[i] = rand.nextFloat();
        //for-in
        for (float x : f)
            System.out.println(x);

    }
}
